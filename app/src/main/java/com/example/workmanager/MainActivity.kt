package com.example.workmanager

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.annotation.RequiresApi
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Observer
import androidx.work.*
import com.example.workmanager.chainofworkers.ApplyFilterWorker
import com.example.workmanager.chainofworkers.DownloadImageWorker
import com.example.workmanager.chainofworkers.SaveImageWorker
import com.example.workmanager.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.MutableStateFlow
import java.time.Duration
import java.util.UUID

class MainActivity : AppCompatActivity() {
    private lateinit var binding:ActivityMainBinding
    private val workManager=WorkManager.getInstance(this)

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding=ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnSendMessage.setOnClickListener(View.OnClickListener {
            val downloadImageWorkRequest= PeriodicWorkRequestBuilder<DownloadImageWorker>(
                Duration.ofHours(12)
            )
        })

    }
}