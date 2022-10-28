package ru.examples.myservices

import android.app.IntentService
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.util.Log

class MyIntentService2 : IntentService(NAME){
    override fun onCreate() {
        super.onCreate()
        log("onCreate")
        setIntentRedelivery(true) // если система убьет наш сервис, он будет перезапущен с нашим интентом.
        // Это вместо STICKY и NOT STICKY, START_REDELIVER_INTENT
        //createNotificationChannel()
        //startForeground(NOTIFICATION_ID, createNotification()) // нужно, также, создать уведомление
    }

    override fun onHandleIntent(intent: Intent?) {
        log("onHandleIntent")
        var page = intent?.getIntExtra(PAGE, 0) ?: 0
        for (i in 0 until 100) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        log("onDestroy")
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyService: $message")
    }


    companion object {

        private const val NAME = "MyIntentService2"
        private const val PAGE = "page"

        fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyIntentService::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}