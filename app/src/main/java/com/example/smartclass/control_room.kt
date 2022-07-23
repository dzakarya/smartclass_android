package com.example.smartclass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class control_room : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_control_room)
        val light_button = findViewById<Button>(R.id.light_button)
        val temp_button = findViewById<Button>(R.id.temp_button)
        val datalog_button = findViewById<Button>(R.id.datalog_button)

        val light_intent = Intent(this,light_control_page::class.java)
        val temp_intent = Intent(this, temp_control_page::class.java)
        val datalog_intent = Intent(this, data_log::class.java)


        light_button.setOnClickListener {
            startActivity(light_intent)
        }
        temp_button.setOnClickListener {
            startActivity(temp_intent)
        }
        datalog_button.setOnClickListener {
            startActivity(datalog_intent)
        }

    }
}