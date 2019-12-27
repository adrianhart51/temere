package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.notification

import android.app.AlarmManager
import android.app.IntentService
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.SystemClock
import androidx.core.content.ContextCompat.getSystemService
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.R
import java.util.*

class NotificationService : IntentService("NotificationBackgroundService") {
    companion object {

        fun scheduleDailyNotification(notification: Notification, context: Context) {
            val myIntent = Intent(context, NotificationPublisher::class.java)
            myIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1)
            myIntent.putExtra(NotificationPublisher.NOTIFICATION, notification)
            val pendingIntent =
                PendingIntent.getBroadcast(context, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val firingCal: Calendar = Calendar.getInstance()
            val currentCal: Calendar = Calendar.getInstance()

            firingCal.set(Calendar.HOUR, 0)
            firingCal.set(Calendar.MINUTE, 0)
            firingCal.set(Calendar.SECOND, 0)

            var intendedTime: Long = firingCal.getTimeInMillis()
            val currentTime: Long = currentCal.getTimeInMillis()

            if (intendedTime >= currentTime) {
                alarmManager!!.setRepeating(
                    AlarmManager.RTC,
                    intendedTime,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            } else {
                intendedTime = firingCal.getTimeInMillis()
                alarmManager!!.setRepeating(
                    AlarmManager.RTC,
                    intendedTime,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            }
        }

        fun getNotification(content: String, context: Context): Notification {
            val builder: Notification.Builder = Notification.Builder(context)
            builder.setContentTitle("Temere")
            builder.setContentText(content)
            builder.setSmallIcon(R.drawable.ic_launcher_background)
            return builder.build()
        }
    }

    override fun onHandleIntent(p0: Intent?) {
        val notification = getNotification("Check this nearby restaurants", baseContext)
        scheduleDailyNotification(notification, baseContext)
    }
}