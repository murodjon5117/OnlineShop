package com.project.onlineshop.service

import android.app.PendingIntent
import android.content.Intent
import android.media.MediaActionSound
import android.media.Ringtone
import android.media.RingtoneManager
import android.media.audiofx.BassBoost
import android.net.Uri
import android.util.Log
import androidx.core.app.NotificationCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.project.onlineshop.BuildConfig
import com.project.onlineshop.screen.MainActivity
import com.project.onlineshop.utils.PrefUtils

class AppFirebaseMessagingService : FirebaseMessagingService() {
    override fun onNewToken(p0: String) {
        Log.d("tab-debug :",p0)
        PrefUtils.setFCMToken(p0)
        Log.i("NFCToken1","Token $p0")
    }

    /*override fun onMessageReceived(p0: RemoteMessage) {
        super.onMessageReceived(p0)
        try {
            val title=p0.notification?.title
            val body=p0.notification?.body
            showMessage(title?."",body?."")
        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    private fun showMessage(title: String, body: String, id:Long=System.currentTimeMillis()) {
        val defaultSound: Uri?=RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val intent= Intent(this,MainActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent=PendingIntent.getActivities(
            this,
            0,
            intent,
            PendingIntent.FLAG_ONE_SHOT
        )
        val channelId= BuildConfig.APPLICATION_ID
        val builder=
            NotificationCompat.Builder(this,channelId)
                .setStyle(NotificationCompat.BigTextStyle)

    }*/
}