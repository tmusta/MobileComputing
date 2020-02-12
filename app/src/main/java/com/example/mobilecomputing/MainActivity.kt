package com.example.mobilecomputing

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.example.mobilecomputing.R.layout.activity_main as activity_main1
import android.widget.Toast
import androidx.room.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.toast
import org.jetbrains.anko.uiThread

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main1)
        /*
        val button: android.widget.Button = findViewById(R.id.fab)
        // Register the onClick listener with the implementation above
        button.setOnClickListener {

        }*/

        var fabOpened = false
        fab.setOnClickListener {
            if (!fabOpened) {
                fabOpened = true
                fab_map.animate().alpha(1f)
                fab_time.animate().alpha(1f)
            }
            else {
                fabOpened = false
                fab_map.animate().alpha(0f)
                fab_time.animate().alpha(0f)
            }

        }
        //val button2: android.widget.Button = findViewById(R.id.fab_time)
        // Register the onClick listener with the implementation above
        fab_time.setOnClickListener {
            val intent = Intent(applicationContext, TimeActivity::class.java)
            startActivity(intent)
        }
        //val button3: android.widget.Button = findViewById(R.id.fab_map)
        // Register the onClick listener with the implementation above
        fab_map.setOnClickListener {
            val intent = Intent(applicationContext, MapActivity::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
        refreshlist()


    }
    private fun refreshlist() {

        doAsync {

            val db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "reminder").build()
            val reminders = db.reminderDao().getReminders()
            db.close()
            uiThread {

                if (reminders.isNotEmpty()) {

                    val adapter = RemindAdapter(applicationContext, reminders)
                    list.adapter = adapter
                } else {
                    toast("No reminders")
                }


            }
        }
    }
}
