package com.cursydev.masteryhub.crash

import android.app.Activity
import android.app.Application
import android.content.*
import android.content.pm.PackageManager
import android.content.res.Resources
import android.os.Build
import android.os.Bundle
import android.os.Process
import android.text.TextUtils
import android.view.Menu
import android.view.MenuItem
import android.view.ViewGroup
import android.widget.HorizontalScrollView
import android.widget.ScrollView
import android.widget.TextView
import java.io.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.system.exitProcess

class CrashHandler {

    val DEFAULT_UNCAUGHT_EXCEPTION_HANDLER: Thread.UncaughtExceptionHandler? =
        Thread.getDefaultUncaughtExceptionHandler()

    fun init(app: Application) {
        init(app, null)
    }

    private fun init(app: Application, crashDir: String?) {
        Thread.setDefaultUncaughtExceptionHandler(object : Thread.UncaughtExceptionHandler {
            override fun uncaughtException(thread: Thread, throwable: Throwable) {
                try {
                    tryUncaughtException(thread, throwable)
                } catch (e: Throwable) {
                    e.printStackTrace()
                    DEFAULT_UNCAUGHT_EXCEPTION_HANDLER?.uncaughtException(
                        thread,
                        throwable
                    )
                }
            }

            private fun tryUncaughtException(thread: Thread, throwable: Throwable) {
                val time: String = SimpleDateFormat("yyyy_MM_dd-HH_mm_ss", Locale.US).format(Date())
                val crashFile = File(
                    if (TextUtils.isEmpty(crashDir)) File(
                        app.getExternalFilesDir(null),
                        "crash"
                    ) else File(crashDir!!),
                    "crash_$time.txt"
                )
                var versionName = "unknown"
                var versionCode: Long = 0
                try {
                    val packageInfo = app.packageManager.getPackageInfo(app.packageName, 0)
                    versionName = packageInfo.versionName
                    versionCode =
                        if (Build.VERSION.SDK_INT >= 28) packageInfo.longVersionCode else packageInfo.versionCode.toLong()
                } catch (ignored: PackageManager.NameNotFoundException) {
                }
                var fullStackTrace: String
                run {
                    val sw = StringWriter()
                    val pw = PrintWriter(sw)
                    throwable.printStackTrace(pw)
                    fullStackTrace = sw.toString()
                    pw.close()
                }
                val sb = StringBuilder()
                sb.append("************* Crash Head ****************\n")
                sb.append("Time Of Crash      : ").append(time).append("\n")
                sb.append("Device Manufacturer: ").append(Build.MANUFACTURER).append("\n")
                sb.append("Device Model       : ").append(Build.MODEL).append("\n")
                sb.append("Android Version    : ").append(Build.VERSION.RELEASE).append("\n")
                sb.append("Android SDK        : ").append(Build.VERSION.SDK_INT).append("\n")
                sb.append("App VersionName    : ").append(versionName).append("\n")
                sb.append("App VersionCode    : ").append(versionCode).append("\n")
                sb.append("************* Crash Head ****************\n")
                sb.append("\n").append(fullStackTrace)
                val errorLog = sb.toString()
                try {
                    writeFile(crashFile, errorLog)
                } catch (ignored: IOException) {
                }

                val intent = Intent(app, CrashActivity::class.java)
                intent.addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                            or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                )
                intent.putExtra(CrashActivity.EXTRA_CRASH_INFO, errorLog)
                try {
                    app.startActivity(intent)
                    Process.killProcess(Process.myPid())
                    exitProcess(0)
                } catch (e: ActivityNotFoundException) {
                    e.printStackTrace()
                    DEFAULT_UNCAUGHT_EXCEPTION_HANDLER?.uncaughtException(
                        thread,
                        throwable
                    )
                }

            }

            @Throws(IOException::class)
            private fun writeFile(file: File, content: String) {
                val parentFile: File = file.parentFile!!
                if (!parentFile.exists()) {
                    parentFile.mkdirs()
                }
                file.createNewFile()
                val fos = FileOutputStream(file)
                fos.write(content.toByteArray())
                try {
                    fos.close()
                } catch (e: IOException) {
                }
            }
        })
    }

    class CrashActivity : Activity(), MenuItem.OnMenuItemClickListener {
        private var mLog: String? = null
        override fun onCreate(savedInstanceState: Bundle?) {
            super.onCreate(savedInstanceState)
            setTheme(android.R.style.Theme_DeviceDefault)
            mLog = intent.getStringExtra(EXTRA_CRASH_INFO)
            val contentView = ScrollView(this)
            contentView.isFillViewport = true
            val hw = HorizontalScrollView(this)
            val message = TextView(this)
            run {
                val padding = dp2px(16f)
                message.setPadding(padding, padding, padding, padding)
                message.text = mLog
                message.setTextIsSelectable(true)
            }
            hw.addView(message)
            contentView.addView(
                hw,
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.MATCH_PARENT
            )
            setContentView(contentView)

        }

        override fun onBackPressed() {
            restart()
        }

        private fun restart() {
            val pm = packageManager
            val intent = pm.getLaunchIntentForPackage(packageName)
            if (intent != null) {
                intent.addFlags(
                    Intent.FLAG_ACTIVITY_NEW_TASK
                            or Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_CLEAR_TASK
                )
                startActivity(intent)
            }
            finish()
            Process.killProcess(Process.myPid())
            exitProcess(0)
        }

        private fun dp2px(dpValue: Float): Int {
            val scale: Float = Resources.getSystem().displayMetrics.density
            return (dpValue * scale + 0.5f).toInt()
        }

        override fun onMenuItemClick(item: MenuItem): Boolean {
            when (item.itemId) {
                android.R.id.copy -> {
                    val cm: ClipboardManager =
                        getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                    cm.setPrimaryClip(ClipData.newPlainText(packageName, mLog))
                }
            }
            return false
        }

        override fun onCreateOptionsMenu(menu: Menu): Boolean {
            menu.add(0, android.R.id.copy, 0, android.R.string.copy)
                .setOnMenuItemClickListener(this)
                .setShowAsAction(MenuItem.SHOW_AS_ACTION_IF_ROOM)
            return true
        }

        companion object {
            const val EXTRA_CRASH_INFO = "crashInfo"
        }
    }
}