package com.example.workmanager

import android.app.Notification
import android.content.Context
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.work.CoroutineWorker
import androidx.work.ForegroundInfo
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class SendMessageWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters
) : CoroutineWorker(context, workerParameters) {

    override suspend fun doWork(): Result {
        val messageText=workerParameters.inputData.getString("message") ?: return Result.failure()
        setForeground(getForegroundInfo())
        delay(3000)
        Log.i("WorkerMessage","Message was sent : $messageText")
        return Result.success()
    }
    private fun getNotification():Notification{
        val notification=NotificationCompat.Builder(context,"sendMessage")
            .setContentTitle("Sending message")
            .setContentText("Your message is sending")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .build()

        return notification
    }

    override suspend fun getForegroundInfo(): ForegroundInfo {
        return ForegroundInfo(1,getNotification())
    }
}