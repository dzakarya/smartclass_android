package com.example.smartclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.github.kittinunf.fuel.httpGet

class light_control_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light_control_page)

        val btn_set_value = findViewById<Button>(R.id.btn_set_light)
        val httpReq = http_req()
        val endPointget = "light/get-light"
        var res = httpReq.http_get(endPointget)
        var zone1 = 32
        var zone2 = 15
        var entPointSet = "light/set-light"
        var data = """
            {
            "zone1":"$zone1",
            "zone2":"$zone2"
            }
        """.trimIndent()
        btn_set_value.setOnClickListener {
            var result = httpReq.http_post(data,entPointSet)
        }
//        var light_value = HttpReq.http_get(endPoint)
    }
}