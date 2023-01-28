package com.example.workmanager.chainofworkers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import kotlinx.coroutines.delay

class SaveImageWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters
) : CoroutineWorker(context,workerParameters) {
    override suspend fun doWork(): Result {
        val imageName=workerParameters.inputData.getString("filter")?:return Result.failure()
        delay(2000)
        Log.i("SaveWorker","Image saved $imageName")
        return Result.success()
    }
}