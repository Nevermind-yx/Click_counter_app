package com.example.click_counter // MAKE SURE THIS MATCHES YOUR FILE

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val tvCount = findViewById<TextView>(R.id.tvCount)
        val btnAdd = findViewById<Button>(R.id.btnAdd)
        var count = 0

        btnAdd.setOnClickListener {
            count++
            tvCount.text = count.toString()
        }
    }
}