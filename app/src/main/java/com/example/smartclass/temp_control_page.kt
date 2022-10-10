package com.example.smartclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.google.android.material.slider.Slider

class temp_control_page : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_temp_control_page)

        val btn_set_value = findViewById<Button>(R.id.btn_set_temp)
        val temp_slider = findViewById<Slider>(R.id.temp_slider)
        val btn_off_value = findViewById<Button>(R.id.btn_off_temp)

        val HttpReq = http_req()
        val endPointget = "temp/get-temp"
        val endPointset = "temp/set-temp"

        var temp_value: Float
        val res =  HttpReq.http_get(endPointget)

        try{
            if (res != null){
                temp_value = res.toFloat()
            }
            else{
                temp_value=16.0f
            }
        }
        catch (exception : Exception){
            temp_value=16.0f
        }

        temp_slider.value=temp_value

        temp_slider.addOnChangeListener { slider, value, fromUser ->
            temp_value = value
        }

        btn_set_value.setOnClickListener {
            val data = """
            {
            "value":"$temp_value"
            }
        """.trimIndent()
            HttpReq.http_post(data,endPointset)
        }

        btn_off_value.setOnClickListener {
            val data = """
            {
            "value":"0"
            }
        """.trimIndent()
            HttpReq.http_post(data,endPointset)

        }




    }
}