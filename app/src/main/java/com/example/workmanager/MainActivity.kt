package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.workmanager.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val workManager=WorkManager.getInstance(this)

    private var loading= MutableLiveData(false)
    private var workerId:UUID?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSendMessage.setOnClickListener(View.OnClickListener {
            val sendMessageWorkRequest= OneTimeWorkRequestBuilder<SendMessageWorker>()
                .setInputData(workDataOf("message" to binding.etData.text.toString()))
                .setConstraints(
                    Constraints.Builder().setRequiredNetworkType(NetworkType.CONNECTED).build()
                ).build()
            workManager.enqueue(sendMessageWorkRequest)
            workerId=sendMessageWorkRequest.id

            observeWorkerState()
            progressBarVisibility()
        })

    }
    private fun progressBarVisibility(){
        //Hide or show progress bar depends on worker progress bar state
        loading.observe(this, Observer {loading->
            if(loading)binding.progressBar.visibility=View.VISIBLE
            else binding.progressBar.visibility=View.INVISIBLE
        })
    }
    private fun observeWorkerState(){
        workerId?.let {
            workManager.getWorkInfoByIdLiveData(it).observe(this, Observer {workInfo->
                if(workInfo!=null){
                    loading.value=workInfo.progress.getBoolean("loading" ,false)
                }
            })
        }
    }
}