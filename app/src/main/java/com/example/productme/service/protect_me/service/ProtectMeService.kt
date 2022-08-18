package com.example.productme.service.protect_me.service

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.Network
import android.net.NetworkCapabilities
import android.net.NetworkRequest
import android.os.Build
import android.util.Log
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.lifecycle.LifecycleService
import androidx.lifecycle.lifecycleScope
import com.example.productme.R
import com.example.productme.core.domian.use_case.CoreUseCases
import com.example.productme.core.util.network.ConnectivityLiveData
import com.example.productme.core.util.network.Resource
import com.example.productme.feature_protect.domain.model.Guard
import com.example.productme.feature_protect.domain.use_case.ProtectMeUseCases
import com.example.productme.service.protect_me.data.remote.request.SendMessageReqBody
import com.example.productme.service.protect_me.domain.repository.ProtectMeServiceRepository
import com.example.productme.service.protect_me.utils.ProtectMeServiceComm.ACTION_PAUSE_SERVICE
import com.example.productme.service.protect_me.utils.ProtectMeServiceComm.ACTION_START_OR_RESUME_SERVICE
import com.example.productme.service.protect_me.utils.ProtectMeServiceComm.ACTION_STOP_SERVICE
import com.example.productme.service.protect_me.utils.ProtectMeServiceComm.NOTIFICATION_CHANNEL_ID
import com.example.productme.service.protect_me.utils.ProtectMeServiceComm.NOTIFICATION_CHANNEL_NAME
import com.example.productme.service.protect_me.utils.ProtectMeServiceComm.NOTIFICATION_ID
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@AndroidEntryPoint
class ProtectMeService : LifecycleService() {
    private val scope = CoroutineScope(Dispatchers.IO)
    private var job: Job? = null
    private var job2: Job? = null
    private var job3: Job? = null

    private lateinit var guard: List<Guard>

    @Inject
    lateinit var repository: ProtectMeServiceRepository

    @Inject
    lateinit var cld: ConnectivityLiveData

    @Inject
    lateinit var useCases: ProtectMeUseCases

    @Inject
    lateinit var coreUseCases: CoreUseCases

    override fun onCreate() {
        super.onCreate()
        cld = ConnectivityLiveData(application)

        useCases.getGuardsUseCase().onEach { guards ->
            guard = guards
        }.launchIn(lifecycleScope)

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        intent?.let { it ->
            when (it.action) {
                ACTION_START_OR_RESUME_SERVICE -> {
                    Log.d("ProService", "started or resume service")

                    checkConnection()
                    checkActivation()
                    startForegroundService()
                }
                ACTION_PAUSE_SERVICE -> {
                    Log.d("ProService", "pause service")
                }
                ACTION_STOP_SERVICE -> {
                    Log.d("ProService", "stop service")
                    stopService()
                    return START_NOT_STICKY
                }
                else -> null
            }
        }
        return START_STICKY
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
        startForeground(
            NOTIFICATION_ID,
            notificationBuild.build()
        )
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
////////////////

    @SuppressLint("LaunchActivityFromNotification")
    private fun showCastumNotification(title: String, content: String) {
        val builder = NotificationCompat.Builder(this, "CHANNEL_ID")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title)
            .setContentText(content)
            .setPriority(NotificationCompat.PRIORITY_HIGH)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = "chanelName"
            val descriptionText = "channeldes"
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel("CHANNEL_ID", name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
        with(NotificationManagerCompat.from(this)) {
            // notificationId is a unique int for each notification that you must define
            notify(6, builder.build())
        }
    }

    //check connection function
    private fun checkConnection() {
        val networkRequest = NetworkRequest.Builder()
            .addCapability(NetworkCapabilities.NET_CAPABILITY_INTERNET)
            .addTransportType(NetworkCapabilities.TRANSPORT_WIFI)
            .addTransportType(NetworkCapabilities.TRANSPORT_CELLULAR)
            .build()
        val networkCallback = object : ConnectivityManager.NetworkCallback() {
            // network is available for use
            override fun onAvailable(network: Network) {
                super.onAvailable(network)
                Toast.makeText(this@ProtectMeService, "wifi is connected", Toast.LENGTH_SHORT)
                    .show()
                job?.cancel()
                job = scope.launch { sendMessage() }
            }

            // Network capabilities have changed for the network
            override fun onCapabilitiesChanged(
                network: Network,
                networkCapabilities: NetworkCapabilities,
            ) {
                super.onCapabilitiesChanged(network, networkCapabilities)
                val unmetered =
                    networkCapabilities.hasCapability(NetworkCapabilities.NET_CAPABILITY_NOT_METERED)
//                Toast.makeText(this@ProtectMeService, "network is change", Toast.LENGTH_SHORT)
//                    .show()
                //sendMessage()
            }

            // lost network connection
            override fun onLost(network: Network) {
                super.onLost(network)
                Toast.makeText(this@ProtectMeService, "wifi is lost", Toast.LENGTH_SHORT).show()
                job?.cancel()
                job = scope.launch {
                    sendMessage()
                }
            }
        }
        val connectivityManager =
            getSystemService(ConnectivityManager::class.java) as ConnectivityManager
        connectivityManager.requestNetwork(networkRequest, networkCallback)

    }

    private suspend fun sendMessage() {
        try {
            guard.forEach {
                val sendMessageReqBody = SendMessageReqBody(
                    To = "whatsapp:+2${it.phone}",
                    From = "whatsapp:+14155238886",
                    Body = "this message sent from an android service please send `join pull-rubber` to continue receiving messages "
                )
                repository.sendMessage(sendMessageReqBody = sendMessageReqBody)
                showCastumNotification(title = "service is trying to send messages",
                    "sent successfully to ${it.phone}")

            }
        } catch (e: Exception) {
            scope.launch {
                showCastumNotification(title = "service is trying to send messages",
                    "an error occurred please check your internet connection ")
                delay(1000 * 10)
                sendMessage()
            }
        }
    }

    private fun checkActivation() {
        Thread {
            kotlin.run {
                while (true) {
                    coreUseCases.checkActivationUseCase().onEach { result ->
                        when (result) {
                            is Resource.Success -> {
                                if (result.data?.isActive == false) {
                                    stopService()
                                    Log.d("activation", "off")
                                    useCases.saveServiceStateUseCase(false)
                                }

                            }
                            is Resource.Error -> {
                                stopService()
                                useCases.saveServiceStateUseCase(false)
                                Log.d("activation", "off")
                            }
                            is Resource.Loading -> {

                            }
                        }
                    }.launchIn(lifecycleScope)
                    Thread.sleep(1000 * 60 * 60 * 2)
                }
            }
        }.start()

    }

}