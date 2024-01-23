package com.kidslearning.kidsplay.smartkids.derdroid.preschool.pushnotification

import android.annotation.SuppressLint
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.media.RingtoneManager
import android.os.AsyncTask
import android.os.Build
import android.os.StrictMode
import android.provider.Settings
import android.text.format.DateUtils
import android.util.Log
import android.widget.RemoteViews
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.kidslearning.kidsplay.smartkids.derdroid.preschool.R
import org.json.JSONObject
import java.io.IOException
import java.net.MalformedURLException
import java.net.URL
import java.util.*


@Suppress("NULLABILITY_MISMATCH_BASED_ON_JAVA_ANNOTATIONS")
public class MyFirebaseMessagingService : FirebaseMessagingService() {


    lateinit var context: Context
    var arrOfImage = ArrayList<String>()

    override fun onMessageReceived(message: RemoteMessage) {
        context = applicationContext
        arrOfImage = ArrayList()

        if (message.data.containsKey("data")) {
            val jsonObject = JSONObject(message.data["data"])
            arrOfImage.add(jsonObject.optString("image1"))
            arrOfImage.add(jsonObject.optString("image2"))
            Log.e("TAG", "onMessageReceived::Image1  " + jsonObject.optString("image1"))
        }


        generateNotification(message)

    }




    private fun generateNotification(p0: RemoteMessage) =
            try {


        val context = this.applicationContext
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

//        mIntent = Intent(context, MainActivity::class.java)
        var mIntent: Intent? = null

//        mIntent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP


        val channelId = "11111"
        try {

            val channelName = context.resources.getString(R.string.app_name)
            val channelDescription = "Application_name Alert"

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                val importance = NotificationManager.IMPORTANCE_HIGH
                val mChannel = NotificationChannel(channelId, channelName, importance)
                mChannel.description = channelDescription

                mChannel.setSound(
                    RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION),
                    mChannel.audioAttributes
                )
                notificationManager.createNotificationChannel(mChannel)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        val builder = NotificationCompat.Builder(this, channelId)

        val expandedView = RemoteViews(packageName, R.layout.item_notification_expand)
        val collapsedView = RemoteViews(packageName, R.layout.item_notification_coll)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            builder.setSmallIcon(R.drawable.notification_icon)
            builder.color = ContextCompat.getColor(context, R.color.colorBlack)

            expandedView.setImageViewResource(R.id.big_icon, R.mipmap.ic_launcher)
            expandedView.setTextViewText(
                R.id.timestamp,
                DateUtils.formatDateTime(
                    this,
                    System.currentTimeMillis(),
                    DateUtils.FORMAT_SHOW_TIME
                )
            )

            collapsedView.setImageViewResource(R.id.big_icon, R.mipmap.ic_launcher)
            collapsedView.setTextViewText(
                R.id.timestamp,
                DateUtils.formatDateTime(
                    this,
                    System.currentTimeMillis(),
                    DateUtils.FORMAT_SHOW_TIME
                )
            )
        } else {
            builder.setSmallIcon(R.drawable.notification_icon)

            expandedView.setImageViewResource(R.id.big_icon, R.mipmap.ic_launcher)
            expandedView.setTextViewText(
                R.id.timestamp,
                DateUtils.formatDateTime(
                    this,
                    System.currentTimeMillis(),
                    DateUtils.FORMAT_SHOW_TIME
                )
            )

            collapsedView.setImageViewResource(R.id.big_icon, R.mipmap.ic_launcher)
            collapsedView.setTextViewText(
                R.id.timestamp,
                DateUtils.formatDateTime(
                    this,
                    System.currentTimeMillis(),
                    DateUtils.FORMAT_SHOW_TIME
                )
            )

        }

        val title = context.resources.getString(R.string.app_name)

        val data = p0.notification
        if (data != null) {

            val jsonObjectData = JSONObject(Gson().toJson(p0.data))
            val Id = jsonObjectData.optString("id")

//                mIntent = Intent(context, MainActivity::class.java)


//            mIntent = Intent(context, WallpaperByCategoryActivity::class.java)
//            val putExtra = mIntent.putExtra("data", Gson().toJson(data))
            mIntent!!.putExtra("notificationData", Gson().toJson(p0.data))
            Log.e("TAG", "generateNotification:::INTENT:::::  ${Gson().toJson(p0.data)}  ")
            mIntent!!.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            try {
                expandedView.setTextViewText(R.id.title_text, data.title)

                expandedView.setTextViewText(R.id.notification_message, data.body)

                collapsedView.setTextViewText(R.id.content_text, data.body)

                collapsedView.setTextViewText(R.id.title_text, data.title)

                val policy = StrictMode.ThreadPolicy.Builder().permitAll().build()
                StrictMode.setThreadPolicy(policy)

                val image1Url = URL(data.imageUrl.toString())

                val bmp1 = BitmapFactory.decodeStream(image1Url.openConnection().getInputStream())
                if (bmp1 != null) {
                    builder.setStyle(
                        NotificationCompat.BigPictureStyle().bigPicture(bmp1)
                            .setSummaryText(data.body)
                    )

                    expandedView.setBitmap(R.id.notification_img, "setImageBitmap", bmp1)
                }


            } catch (e: Exception) {
                e.printStackTrace()
                builder.setContentTitle(title)
            }
        } else {
            val jsonObjectData = JSONObject(Gson().toJson(p0.data))
            val Id = jsonObjectData.optString("id")
//                mIntent!! = Intent(context, MainActivity::class.java)


//            mIntent!! = Intent(context, WallpaperByCategoryActivity::class.java)
            mIntent!!.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
            builder.setContentText(title)
        }

        builder.setCustomContentView(collapsedView)
        builder.setCustomBigContentView(expandedView)
        builder.setShowWhen(false)
        builder.setStyle(NotificationCompat.DecoratedCustomViewStyle())
        builder.priority = NotificationCompat.PRIORITY_HIGH
        builder.setAutoCancel(true)
        builder.setVisibility(NotificationCompat.VISIBILITY_SECRET)
        val pendingIntent = PendingIntent.getActivity(
            context,
            System.currentTimeMillis().toInt(),
            mIntent!!,
            PendingIntent.FLAG_CANCEL_CURRENT
        )

//        val pendingIntent = PendingIntent.getActivity(context, System.currentTimeMillis().toInt(), mIntent!!, PendingIntent.FLAG_CANCEL_CURRENT)
        builder.setContentIntent(pendingIntent)

        builder.setSound(RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION))
        notificationManager.notify(System.currentTimeMillis().toInt(), builder.build())
    } catch (e: Exception) {
        e.printStackTrace()
    }


    private lateinit var deviceId: String
    private lateinit var token: String

    @SuppressLint("HardwareIds")
    override fun onNewToken(p0: String) {
        super.onNewToken(p0)
        deviceId = Settings.Secure.getString(
            applicationContext.contentResolver,
            Settings.Secure.ANDROID_ID
        )
        token = p0
//        SendTokenId(applicationContext, deviceId, token, this@MyFirebaseMessagingService)
        Log.e("Firebase Token", p0)
        Log.e("DeviceId", deviceId)

    }


    lateinit var icon1: Bitmap
    lateinit var icon2: Bitmap
    lateinit var icon: Bitmap

    var arrOfBitmap = ArrayList<Bitmap>()

    @SuppressLint("StaticFieldLeak")
    inner class DownloadImageBitmap(var stringUrl: String) : AsyncTask<String, Void, Void>() {

        override fun doInBackground(vararg strings: String): Void? {
            var url: URL? = null
            try {
                url = URL(stringUrl)
                icon1 = BitmapFactory.decodeStream(url.openConnection().getInputStream())
                arrOfBitmap.add(icon1)
            } catch (e: MalformedURLException) {
                e.printStackTrace()
            } catch (e: IOException) {
                e.printStackTrace()
            }
            return null
        }

        override fun onPostExecute(result: Void?) {
            super.onPostExecute(result)
            Download1ImageBitmap(arrOfImage[1]).execute()
        }
    }


    @SuppressLint("StaticFieldLeak")
    inner class Download1ImageBitmap(var stringUrl: String) : AsyncTask<String, Void, Void>() {

        override fun doInBackground(vararg strings: String): Void? {
            return null
        }
    }


}