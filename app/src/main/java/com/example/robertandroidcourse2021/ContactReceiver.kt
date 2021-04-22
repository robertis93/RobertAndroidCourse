package com.example.robertandroidcourse2021

import android.app.Notification

import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Intent
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.os.Build
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat

class ContactReceiver : BroadcastReceiver() {

    val channel_id = "my_channel"
    val notification_id = 1

    override fun onReceive(context: Context, intent: Intent) {
        val text = intent.getStringExtra("text")
        val id = intent.getIntExtra("id", -1)
        if (text != null && id != -1) {

            createNotificationChannel(context)
            val intent = Intent(context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            intent.putExtra("contact_id", id)
            val pendingIntent= PendingIntent.getActivity(context, 0, intent, 0)

            val notification = NotificationCompat.Builder(context, channel_id)
                .setSmallIcon(R.drawable.usein)
                .setContentTitle("Оповещение")
                .setContentText(text)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setContentIntent(pendingIntent)
                .build()
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.notify(notification_id, notification)
        }
    }

    private fun createNotificationChannel(context: Context) {
        // Create the NotificationChannel, but only on API 26+ because
        // the NotificationChannel class is new and not in the support library
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val name = context.getString(R.string.notification_channel_name)
            val descriptionText = context.getString(R.string.channel_describtion)
            val importance = NotificationManager.IMPORTANCE_DEFAULT
            val channel = NotificationChannel(channel_id, name, importance).apply {
                description = descriptionText
            }
            // Register the channel with the system
            val notificationManager: NotificationManager =
                context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(channel)
        }
    }

}