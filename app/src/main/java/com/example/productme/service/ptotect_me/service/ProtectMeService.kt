package com.example.productme.service.ptotect_me.service

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import com.example.productme.service.ptotect_me.domain.repository.ProtectMeServiceRepository
import com.example.productme.service.ptotect_me.service.ProtectMeServiceComm.ACTION_PAUSE_SERVICE
import com.example.productme.service.ptotect_me.service.ProtectMeServiceComm.ACTION_START_OR_RESUME_SERVICE
import com.example.productme.service.ptotect_me.service.ProtectMeServiceComm.ACTION_STOP_SERVICE
import com.example.productme.service.ptotect_me.service.ProtectMeServiceComm.NOTIFICATION_CHANNEL_ID
import com.example.productme.service.ptotect_me.service.ProtectMeServiceComm.NOTIFICATION_CHANNEL_NAME
import com.example.productme.service.ptotect_me.service.ProtectMeServiceComm.NOTIFICATION_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.IOException
import javax.inject.Inject

@AndroidEntryPoint
class ProtectMeService:Service() {
    private val scope = CoroutineScope(Dispatchers.IO)

    @Inject
    lateinit var repository: ProtectMeServiceRepository

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let {
            when (it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    Log.d("ProService", "started or resume service")
                    scope.launch {
                        while (true) {
                            try{
                            repository.sendMessage(ProtectMeServiceComm.sentMessageReqBody)
                            delay(1000*60*60*1)
                            }catch (e:IOException){
                                e.printStackTrace()
                            }
                        }
                    }.start()
                    startForegroundService()

                }
                ACTION_PAUSE_SERVICE -> {
                    Log.d("ProService", "pause service")
                }
                ACTION_STOP_SERVICE -> {
                    Log.d("ProService", "stop service")
                    stopService()
                }
                else -> null
            }
        }
        return super.onStartCommand(intent, flags, startId)
    }


    private fun startForegroundService() {
        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel(notificationManager = notificationManager)
        }
        val notificationBuild = NotificationCompat.Builder(
            this, NOTIFICATION_CHANNEL_ID
        )
            .setAutoCancel(false)
            .setOngoing(true)
            .setContentTitle("message sent")
            .setContentText(".........")
        startForeground(NOTIFICATION_ID, notificationBuild.build())
    }

    private fun stopService() {
        stopForeground(true)
        stopSelf()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel(notificationManager: NotificationManager) {
        val channel = NotificationChannel(
            NOTIFICATION_CHANNEL_ID,
            NOTIFICATION_CHANNEL_NAME,
            NotificationManager.IMPORTANCE_DEFAULT
        )
        notificationManager.createNotificationChannel(channel)
    }


    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }
}