package com.example.workmanager

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.workmanager.chainofworkers.ApplyFilterWorker
import com.example.workmanager.chainofworkers.DownloadImageWorker
import com.example.workmanager.chainofworkers.SaveImageWorker
import com.example.workmanager.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.MutableStateFlow
import java.util.UUID

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val workManager=WorkManager.getInstance(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSendMessage.setOnClickListener(View.OnClickListener {
            val downloadImageWorkRequest= OneTimeWorkRequest.from(DownloadImageWorker::class.java)
            val applyFilterWorkRequest= OneTimeWorkRequest.from(ApplyFilterWorker::class.java)
            val saveImageWorkRequest= OneTimeWorkRequest.from(SaveImageWorker::class.java)

            //this will make a request multiple time
/*            workManager.beginWith(downloadImageWorkRequest).then(applyFilterWorkRequest)
                .then(saveImageWorkRequest).enqueue()*/

            workManager.beginUniqueWork("image",ExistingWorkPolicy.KEEP,downloadImageWorkRequest)
                .then(applyFilterWorkRequest)
                .then(saveImageWorkRequest).enqueue()
        })

    }
}