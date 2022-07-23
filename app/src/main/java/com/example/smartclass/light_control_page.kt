package com.example.smartclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class light_control_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light_control_page)

        val btn_set_value = findViewById<Button>(R.id.btn_set_light)
        val HttpReq = http_req()
        val endPoint = "/set-light"
        var data = "value=50&zone=1"
        btn_set_value.setOnClickListener {
            HttpReq.http_post(data, endPoint)
        }
    }
}