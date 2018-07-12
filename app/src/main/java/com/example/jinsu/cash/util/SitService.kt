package com.example.jinsu.cash.util

import android.app.*
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Build
import android.os.IBinder
import android.support.annotation.RequiresApi
import android.support.v4.app.NotificationCompat
import android.util.Log
import android.widget.RemoteViews
import com.example.jinsu.cash.R
import com.example.jinsu.cash.activity.LoginActivity
import android.app.NotificationManager
import android.app.NotificationChannel
import com.example.jinsu.cash.activity.MainActivity







class SitService : Service() {

    private var Notifi_M: NotificationManager? = null
    // private ServiceThread thread;

    private var Notifi: Notification? = null
    private var remoteViews: RemoteViews? = null

    override fun onCreate() {
        super.onCreate()
        val myFilteredResponse = Intent("make.a.yong.sit")
        myFilteredResponse.putExtra("stepService", "gogo")
        sendBroadcast(myFilteredResponse)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }

    } // end of onCreate


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        Log.d("sit_service","서비스 들어옴")
         super.onStartCommand(intent, flags, startId)
        val intent2 = Intent(this@SitService, LoginActivity::class.java)
        intent2.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pendingIntent = PendingIntent.getActivity(this@SitService, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT)
        remoteViews = RemoteViews(getPackageName(), R.layout.remote_barcode)
        remoteViews!!.setOnClickPendingIntent(R.id.layout_remote, pendingIntent)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            Notifi_M = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

// Sets an ID for the notification, so it can be updated.
            val notifyID = 1

// The id of the channel.
            val CHANNEL_ID = "my_channel_01"

// Create a notification and set the notification channel.
            val notification = Notification.Builder(this@SitService)
                    .setContentTitle("New Message")
                    .setContentText("You've received new messages.")
                    .setContent(remoteViews)
                    .setSmallIcon(R.drawable.camera)
                    .setChannelId(CHANNEL_ID)
                    .setOngoing(true)
                    .build()

// Issue the notification.
            Notifi_M!!.notify(1, notification)
        }
        else {

            Notifi_M = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
            Notifi = NotificationCompat.Builder(this@SitService)
                    .setContent(remoteViews)
                    .setPriority(Notification.PRIORITY_MIN)
                    .setSmallIcon(R.drawable.camera)
                    .setTicker("알림!!!")
                    .setContentIntent(pendingIntent)
                    .setWhen(0)
                    .setOngoing(true)
                    .setContentTitle("fffff")
                    .setContentText("dsfdsfsdf")
                    .build()


            //Notifi_M?.notify( 1 , Notifi)
            startForeground(1, Notifi)
        }
            return Service.START_NOT_STICKY
            //return super.onStartCommand(intent, flags, startId)

    } // end of onStartCommand

    override fun onDestroy() {
        super.onDestroy()
        Log.i("onDestroy", "IN")

    } // end of onDestroy

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun createNotificationChannel(){
        val mNotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

// The id of the channel.
        val id = "my_channel_01"

// The user-visible name of the channel.
        val name = "name"

// The user-visible description of the channel.
        val description = "description"

        val importance = NotificationManager.IMPORTANCE_LOW

        val mChannel = NotificationChannel(id, name, importance)

// Configure the notification channel.
        mChannel.description = description
        mChannel.enableLights(true)

// Sets the notification light color for notifications posted to this
// channel, if the device supports this feature.
        mChannel.lightColor = Color.RED
        mChannel.enableVibration(true)
        mChannel.vibrationPattern = longArrayOf(100, 200, 300, 400, 500, 400, 300, 200, 400)

        mNotificationManager.createNotificationChannel(mChannel)

    }
}