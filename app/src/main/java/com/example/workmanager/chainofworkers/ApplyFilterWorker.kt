package com.example.workmanager.chainofworkers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import androidx.work.workDataOf
import kotlinx.coroutines.delay

class ApplyFilterWorker(
    private val context: Context,
    private val workerParameters: WorkerParameters
) : CoroutineWorker(context,workerParameters) {
    override suspend fun doWork(): Result {
        val imageName=workerParameters.inputData.getString("image")?:return Result.failure()
        delay(2000)
        Log.i("FilterWorker","Applied filter : $imageName")
        return Result.success(workDataOf("filter" to imageName))
    }
}