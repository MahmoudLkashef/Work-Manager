package com.example.workmanager

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build

class WorkManagerApplication:Application() {
    override fun onCreate() {
        super.onCreate()

        if(Build.VERSION.SDK_INT >= 26){
            val channel=NotificationChannel(
                "sendMessage",
                "Send message notification",
                NotificationManager.IMPORTANCE_DEFAULT
            )

            val notificationManager=getSystemService(NotificationManager::class.java)
            notificationManager.createNotificationChannel(channel)
        }
    }
}