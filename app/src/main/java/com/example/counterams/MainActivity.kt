package com.example.counterams

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    internal lateinit var tapMeButton: Button
    internal lateinit var counter: TextView
    internal lateinit var time: TextView
    internal var count = 0
    internal var timer = 60


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tapMeButton = findViewById(R.id.tapMeButton)
        counter = findViewById(R.id.counter)
        time = findViewById(R.id.time)
        tapMeButton.setOnClickListener {
            incrementCounter()
        }
        time.text=getString(R.string.timeText,timer)
    }

    private fun incrementCounter() {

        count = count + 1
        counter.text = "comtador: "+count.toString()


    }


}