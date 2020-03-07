package com.example.mobilecomputing

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.icu.util.GregorianCalendar
import android.os.Bundle
import com.google.android.material.snackbar.Snackbar
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Room

import kotlinx.android.synthetic.main.activity_time.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.runOnUiThread
import org.jetbrains.anko.toast
import java.util.*

class TimeActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_time)
        time_create.setOnClickListener{


             val calendar = GregorianCalendar(
                datePicker.year,
                datePicker.month,
                datePicker.dayOfMonth,
                timePicker.hour,
                timePicker.minute
            )
            if ((at_message.text.toString() != "") &&
                    (calendar.timeInMillis > System.currentTimeMillis())) {

                        val reminder = Remind(
                            uid = null,
                            time = calendar.timeInMillis,
                            location = null,
                            message = at_message.text.toString()

                        )

                        doAsync {
                            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "reminder").build()
                            db.reminderDao().insert(reminder)
                            db.close()

                            setAlarm(reminder.time!!, reminder.message)

                            finish()

                        }
                    }
            else {
                toast("Invalid")

            }
        }
    }
    private fun setAlarm(time: Long, message: String) {

        val intent = Intent(this, RemindReceiver::class.java)
        intent.putExtra("message", message)

        val pendingIntent = PendingIntent.getBroadcast(this, 1, intent, PendingIntent.FLAG_ONE_SHOT)

        val manager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
        manager.setExact(AlarmManager.RTC, time, pendingIntent)

        //runOnUiThread(toast("Reminder is created!!!"))
        runOnUiThread { toast("Reminder is created !!!1!!") }

    }
}
