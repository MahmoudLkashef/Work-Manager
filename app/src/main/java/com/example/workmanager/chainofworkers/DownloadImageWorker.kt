package com.example.workmanager.chainofworkers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay
import java.util.UUID

class DownloadImageWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters
) : CoroutineWorker(context,workerParameters) {
    override suspend fun doWork(): Result {
        delay(2000)
        Log.i("DownloadWorker","Image downloaded")
        return Result.success(workDataOf("image" to UUID.randomUUID().toString()))
    }
}