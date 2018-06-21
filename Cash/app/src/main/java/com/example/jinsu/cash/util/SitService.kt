package com.example.jinsu.cash.util

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.app.Service
import android.content.Context
import android.content.Intent
import android.os.IBinder
import android.util.Log
import android.widget.RemoteViews
import com.example.jinsu.cash.R
import com.example.jinsu.cash.activity.LoginActivity

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


    } // end of onCreate


    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {

        super.onStartCommand(intent, flags, startId)


        val intent2 = Intent(this@SitService, LoginActivity::class.java)
        val pendingIntent = PendingIntent.getActivity(this@SitService, 0, intent2, PendingIntent.FLAG_UPDATE_CURRENT)

        remoteViews = RemoteViews(getPackageName(), R.layout.remote_barcode)
        remoteViews!!.setOnClickPendingIntent(R.id.layout_remote, pendingIntent)

        Notifi_M = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager?
        Notifi = Notification.Builder(this@SitService)
                .setContent(remoteViews)
                .setPriority(Notification.PRIORITY_MIN)
                .setSmallIcon(R.drawable.camera)
                .setTicker("알림!!!")
                .setContentIntent(pendingIntent)
                .setWhen(0)
                .setOngoing(true)
                .build()

        //Notifi_M.notify( 777 , Notifi);
        startForeground(1, Notifi)

        return Service.START_NOT_STICKY
    } // end of onStartCommand

    override fun onDestroy() {
        super.onDestroy()
        Log.i("onDestroy", "IN")

    } // end of onDestroy

    override fun onBind(intent: Intent): IBinder? {
        return null
    }

    }