package id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.notification

import android.app.AlarmManager
import android.app.Notification
import android.app.PendingIntent
import android.content.Context
import android.content.Context.ALARM_SERVICE
import android.content.Intent
import android.os.SystemClock
import androidx.core.content.ContextCompat.getSystemService
import id.ac.ui.cs.mobileprogramming.adrianhartanto.temere.R
import java.util.*


class NotificationService {
    companion object {
        fun scheduleNotification(notification: Notification, delay: Int, context: Context) {
            val notificationIntent = Intent(context, NotificationPublisher::class.java)
            notificationIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1)
            notificationIntent.putExtra(NotificationPublisher.NOTIFICATION, notification)
            val pendingIntent = PendingIntent.getBroadcast(
                context,
                0,
                notificationIntent,
                PendingIntent.FLAG_UPDATE_CURRENT
            )
            val futureInMillis = SystemClock.elapsedRealtime() + delay
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager[AlarmManager.ELAPSED_REALTIME_WAKEUP, futureInMillis] = pendingIntent
        }

        fun scheduleDailyNotification(notification: Notification, context: Context) {
            val myIntent = Intent(context, NotificationPublisher::class.java)
            myIntent.putExtra(NotificationPublisher.NOTIFICATION_ID, 1)
            myIntent.putExtra(NotificationPublisher.NOTIFICATION, notification)
            val pendingIntent =
                PendingIntent.getBroadcast(context, 0, myIntent, PendingIntent.FLAG_UPDATE_CURRENT)
            val alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager

            val firingCal: Calendar = Calendar.getInstance()
            val currentCal: Calendar = Calendar.getInstance()

            firingCal.set(Calendar.HOUR, 10) // At the hour you wanna fire

            firingCal.set(Calendar.MINUTE, 33) // Particular minute

            firingCal.set(Calendar.SECOND, 0) // particular second


            var intendedTime: Long = firingCal.getTimeInMillis()
            val currentTime: Long = currentCal.getTimeInMillis()

            if (intendedTime >= currentTime) { // you can add buffer time too here to ignore some small differences in milliseconds
// set from today
                alarmManager!!.setRepeating(
                    AlarmManager.RTC,
                    intendedTime,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            } else { // set from next day
// you might consider using calendar.add() for adding one day to the current day
//                firingCal.add(Calendar.DAY_OF_MONTH, 1)
                intendedTime = firingCal.getTimeInMillis()
                alarmManager!!.setRepeating(
                    AlarmManager.RTC,
                    intendedTime,
                    AlarmManager.INTERVAL_DAY,
                    pendingIntent
                )
            }
        }

        fun getNotification(content: String, context: Context): Notification? {
            val builder: Notification.Builder = Notification.Builder(context)
            builder.setContentTitle("Scheduled Notification")
            builder.setContentText(content)
            builder.setSmallIcon(R.drawable.ic_launcher_background)
            return builder.build()
        }
    }
}