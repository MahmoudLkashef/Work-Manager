package com.example.workmanager

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class SendMessageWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        delay(2000)
        Log.i("WorkerMessage","Message was sent")
        return Result.success()
    }
    // If we close the app before delay finished the message will not be sent
    // So we need to convert this worker to foreground service because it has more priority and it will not be killed when the activity is killed
}