package com.example.smartclass

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.speech.RecognizerIntent
import android.widget.*
import com.github.kittinunf.fuel.httpGet
import com.google.android.material.slider.Slider
import com.google.android.material.switchmaterial.SwitchMaterial
import java.util.*
import kotlin.math.log

class light_control_page : AppCompatActivity() {
    lateinit var micIV : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_light_control_page)

        val btn_set_value = findViewById<Button>(R.id.btn_set_light)

        val lh_slider = findViewById<Slider>(R.id.lh_slider1)
        val lh_slider2 = findViewById<Slider>(R.id.lh_slider2)

        val sw_automatic = findViewById<SwitchMaterial>(R.id.swautomation)

        micIV = findViewById(R.id.iv_mic)

        val httpReq = http_req()
        val endPointget = "light/get-light"
        var res = httpReq.http_get(endPointget)
        var zone1 = 0
        var zone2 = 0
        var isAutomate = 0

//
//        //temporary radio button
//        val rg1 = findViewById<RadioGroup>(R.id.rg1)
//        val rg2 = findViewById<RadioGroup>(R.id.rg2)

        micIV.setOnClickListener {
            val intent = Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH)
            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL,
                RecognizerIntent.LANGUAGE_MODEL_FREE_FORM)

            intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, "en-US")
            intent.putExtra(RecognizerIntent.EXTRA_PROMPT, "Speak to text")

            try {
                startActivityForResult(intent, 1)
            } catch (e: Exception) {
                // on below line we are displaying error message in toast
                Toast
                    .makeText(
                        this, " " + e.message,
                        Toast.LENGTH_SHORT
                    )
                    .show()
            }

        }


        lh_slider.addOnChangeListener { slider, value, fromUser ->
            zone1 = value.toInt()
        }

        lh_slider2.addOnChangeListener { slider, value, fromUser ->
            zone2 = value.toInt()
        }

        sw_automatic.setOnClickListener {
            if (sw_automatic.isChecked){
                isAutomate = 1
            }else{
                isAutomate = 0
            }
        }

        btn_set_value.setOnClickListener {
//            var id1 : Int = rg1.checkedRadioButtonId
//            if (id1!=-1){ // If any radio button checked from radio group
//                // Get the instance of radio button using id
//                val radio1:RadioButton = findViewById(id1)
//                if (radio1.text == "Off"){
//                    zone1 = 0
//                }else if(radio1.text == "Low"){
//                    zone1 = 40
//                }else if(radio1.text == "Mid"){
//                    zone1 = 56
//                }else if(radio1.text == "Bright"){
//                    zone1 = 70
//                }else if(radio1.text == "On"){
//                    zone1 = 100
//                }
//            }
//            var id2 : Int = rg2.checkedRadioButtonId
//            if (id2!=-1){ // If any radio button checked from radio group
//                // Get the instance of radio button using id
//                val radio2:RadioButton = findViewById(id2)
//                if (radio2.text == "Off"){
//                    zone2 = 0
//                }else if(radio2.text == "Low"){
//                    zone2 = 40
//                }else if(radio2.text == "Mid"){
//                    zone2 = 56
//                }else if(radio2.text == "Bright"){
//                    zone2 = 70
//                }else if(radio2.text == "On"){
//                    zone2 = 100
//                }
//            }

            var entPointSet = "light/set-light"
            var data = """
            {
            "zone1":"$zone1",
            "zone2":"$zone2",
            "mode":"$isAutomate"
            }
        """.trimIndent()
            var result = httpReq.http_post(data,entPointSet)
        }
//        var light_value = HttpReq.http_get(endPoint)


    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        // in this method we are checking request
        // code with our result code.
        if (requestCode == 1) {
            // on below line we are checking if result code is ok
            if (resultCode == RESULT_OK && data != null) {

                // in that case we are extracting the
                // data from our array list
                val res: ArrayList<String> =
                    data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS) as ArrayList<String>

                // on below line we are setting data
                // to our output text view.

                val httpReq = http_req()

                if(res[0]=="turn on the light"){
                    var entPointSet = "light/set-light"
                    var data = """
                    {
                    "zone1":"100",
                    "zone2":"100",
                    "mode":"0"
                    }
                """.trimIndent()
                    var result = httpReq.http_post(data,entPointSet)
                }
                else if(res[0]=="turn off the light"){
                    var entPointSet = "light/set-light"
                    var data = """
                    {
                    "zone1":"0",
                    "zone2":"0",
                    "mode":"0"
                    }
                """.trimIndent()
                    var result = httpReq.http_post(data,entPointSet)
                }
            }
        }
    }
}