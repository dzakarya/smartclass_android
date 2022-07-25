package com.example.smartclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class temp_control_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp_control_page)

        val btn_set_value = findViewById<Button>(R.id.btn_set_temp)
        val HttpReq = http_req()
        val endPointget = "temp/get-temp"
        val endPointset = "temp/set-temp"
        var value = 20
        var data = """
            {
            "value":"$value"
            }
        """.trimIndent()
        var res =  HttpReq.http_get(endPointget)
        btn_set_value.setOnClickListener {
            HttpReq.http_post(data,endPointset)
        }
    }
}