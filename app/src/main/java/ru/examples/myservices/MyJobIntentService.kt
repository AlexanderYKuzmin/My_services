package ru.examples.myservices

import android.content.Context
import android.content.Intent
import android.util.Log
import androidx.core.app.JobIntentService

class MyJobIntentService : JobIntentService() {

    override fun onHandleWork(intent: Intent) {
        log("onHandleIntent")
        var page = intent.getIntExtra(MyJobIntentService.PAGE, 0)
        for (i in 0 until 5) {
            Thread.sleep(1000)
            log("Timer $i $page")
        }
    }

    private fun log(message: String) {
        Log.d("SERVICE_TAG", "MyService: $message")
    }

    companion object {

        private const val PAGE = "page"
        private const val JOB_ID = 101

        fun enqueue(context: Context, page: Int) {
            JobIntentService.enqueueWork(
                context,
                MyJobIntentService::class.java,
                JOB_ID,
                newIntent(context, page)
            )
        }

        private fun newIntent(context: Context, page: Int): Intent {
            return Intent(context, MyIntentService::class.java).apply {
                putExtra(PAGE, page)
            }
        }
    }
}