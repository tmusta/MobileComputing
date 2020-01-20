package com.example.mobilecomputing

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import com.example.mobilecomputing.R.layout.activity_main as activity_main1
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(activity_main1)

        val button: android.widget.Button = findViewById(R.id.button)
        // Register the onClick listener with the implementation above
        button.setOnClickListener { Toast.makeText(this@MainActivity,
            "You clicked first button",
            Toast.LENGTH_SHORT).show()
        }
        val button2: android.widget.Button = findViewById(R.id.button2)
        // Register the onClick listener with the implementation above
        button2.setOnClickListener { Toast.makeText(this@MainActivity,
            "You clicked second button",
            Toast.LENGTH_SHORT).show()
        }
        val button3: android.widget.Button = findViewById(R.id.button3)
        // Register the onClick listener with the implementation above
        button3.setOnClickListener { Toast.makeText(this@MainActivity,
            "You clicked third button",
            Toast.LENGTH_SHORT).show()
        }
        }
}
