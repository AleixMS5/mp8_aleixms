package com.example.counterams

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
private val INITTAL_TIME =20
    private val TAG =MainActivity::class.java.simpleName
    internal lateinit var tapMeButton: Button
    internal lateinit var counter: TextView
    internal lateinit var time: TextView
    internal var count = 0
    internal var timer = INITTAL_TIME
    internal var appStarted = false
    internal lateinit var countD: CountDownTimer

    //    internal val initialcountD: Long = 60000
    internal val initialcountD: Long = timer.toLong() * 1000
    internal val intervalcountD: Long = 1000

    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        initCountD()
        tapMeButton = findViewById(R.id.tapMeButton)
        counter = findViewById(R.id.counter)
        time = findViewById(R.id.time)
        tapMeButton.setOnClickListener {view->
            if (!appStarted) {
                startGame()
            }
           val bounceAnimation= AnimationUtils.loadAnimation(this,R.anim.bounce)

            view.startAnimation(bounceAnimation)

            incrementCounter()

        }
        time.text = getString(R.string.timeText, timer)
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        super.onCreateOptionsMenu(menu)
        menuInflater.inflate(R.menu.menu,menu)
        return true

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId== R.id.actionAbout){
            showInfo()
        }
        return true
    }

    private fun showInfo() {
       val dialogTitle = getString(R.string.aboutTitle,BuildConfig.VERSION_NAME)
       val dialogMessage = getString(R.string.aboutMessage )

        var builder = AlertDialog.Builder(this)
        builder.setTitle(dialogTitle).setMessage(dialogMessage).create().show()

    }

    private fun startGame() {
        countD.start()
        appStarted = true
    }

    private fun initCountD() {
        countD = object : CountDownTimer(initialcountD, intervalcountD) {
            override fun onTick(p0: Long) {
                val TimeLeft = p0 / 1000

                time.text = getString(R.string.timeText, TimeLeft)
            }

            override fun onFinish() {
                endGame()
            }
        }
    }

    private fun incrementCounter() {

        count = count + 1
        counter.text = getString(R.string.coutText, count)
        val blingAnimation = AnimationUtils.loadAnimation(this,
            R.anim.bling);
        counter.startAnimation(blingAnimation)

    }

    private fun endGame() {
        Toast.makeText(this, getString(R.string.endGame, count), Toast.LENGTH_LONG).show()
        resetGame()
    }

    private fun resetGame() {
        count = 0

        counter.text = getString(R.string.coutText, count)
        timer=INITTAL_TIME
        time.text= getString(R.string.timeText,timer)
        initCountD()

        appStarted = false

    }


}


