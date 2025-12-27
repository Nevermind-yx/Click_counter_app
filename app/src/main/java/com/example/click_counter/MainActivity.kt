package com.example.click_counter // ⚠️ MAKE SURE THIS MATCHES YOUR PACKAGE NAME

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    // Variables to keep track of the game
    var score = 0
    var gameStarted = false
    var timer: CountDownTimer? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // 1. Find all our views
        val tvTimer = findViewById<TextView>(R.id.tvTimer)
        val tvCount = findViewById<TextView>(R.id.tvCount)
        val btnTap = findViewById<Button>(R.id.btnTap)
        val btn10s = findViewById<Button>(R.id.btn10s)
        val btn30s = findViewById<Button>(R.id.btn30s)

        // 2. Logic for "10 Seconds" button
        btn10s.setOnClickListener {
            startGame(10, tvTimer, tvCount, btnTap, btn10s, btn30s)
        }

        // 3. Logic for "30 Seconds" button
        btn30s.setOnClickListener {
            startGame(30, tvTimer, tvCount, btnTap, btn10s, btn30s)
        }

        // 4. Logic for the TAP button
        btnTap.setOnClickListener {
            if (gameStarted) {
                score++
                tvCount.text = score.toString()
            }
        }
    }

    // A function to handle starting the game logic
    private fun startGame(seconds: Int, tvTimer: TextView, tvScore: TextView, btnTap: Button, btn10: Button, btn30: Button) {
        // Reset score
        score = 0
        tvScore.text = "0"
        gameStarted = true

        // Enable Tap button, disable Mode buttons
        btnTap.isEnabled = true
        btn10.isEnabled = false
        btn30.isEnabled = false

        // Cancel old timer if running
        timer?.cancel()

        // Start new timer
        timer = object : CountDownTimer((seconds * 1000).toLong(), 1000) {
            override fun onTick(millisUntilFinished: Long) {
                val secondsLeft = millisUntilFinished / 1000
                tvTimer.text = "Time: ${secondsLeft}s"
            }

            override fun onFinish() {
                tvTimer.text = "Time's Up!"
                gameStarted = false
                btnTap.isEnabled = false // Lock the button
                btn10.isEnabled = true   // Unlock modes
                btn30.isEnabled = true
            }
        }.start()
    }
}