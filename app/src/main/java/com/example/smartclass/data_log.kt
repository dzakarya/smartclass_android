package com.example.smartclass

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.*
import java.util.*

class data_log : AppCompatActivity(), DatePickerDialog.OnDateSetListener, TimePickerDialog.OnTimeSetListener {

    var day = 0
    var month = 0
    var year = 0
    var hour = 0
    var minute = 0

    var savedDay = 0
    var savedMonth = 0
    var savedYear = 0
    var savedHour = 0
    var savedMinute = 0

    lateinit var tv_in_date : TextView
    lateinit var tv_in_time : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_data_log)

        tv_in_date = findViewById(R.id.in_date)
        tv_in_time = findViewById(R.id.in_time)

        val btn_date = findViewById<Button>(R.id.btn_date)
        val btn_time = findViewById<Button>(R.id.btn_time)
        val btn_search_data = findViewById<Button>(R.id.btn_search_data)

        val HttpReq = http_req()

        getDateTimeCalendar()

        btn_date.setOnClickListener {
            DatePickerDialog(this,this,year,month,day).show()
        }

        btn_time.setOnClickListener {
            TimePickerDialog(this,this,hour,minute,true).show()
        }

        btn_search_data.setOnClickListener{
            var payload = """
            {
            "date":"$savedDay-$savedMonth-$savedYear",
            "time":"$savedHour:$savedMinute"
            }
        """.trimIndent()
            var res = HttpReq.http_post(payload,"/data/get-data")
        }
    }

    private fun getDateTimeCalendar(){
        val cal = Calendar.getInstance()
        day = cal.get(Calendar.DAY_OF_MONTH)
        month = cal.get(Calendar.MONTH)
        year = cal.get(Calendar.YEAR)
        hour = cal.get(Calendar.HOUR)
        minute = cal.get(Calendar.MINUTE)
    }

    @SuppressLint("SetTextI18n")
    override fun onDateSet(p0: DatePicker?, p1: Int, p2: Int, p3: Int) {
        savedDay = p3
        savedMonth = p2
        savedYear = p1
        getDateTimeCalendar()
        tv_in_date.text = "$savedDay-$savedMonth-$savedYear"

    }

    @SuppressLint("SetTextI18n")
    override fun onTimeSet(p0: TimePicker?, p1: Int, p2: Int) {
        savedHour = p1
        savedMinute = p2
        getDateTimeCalendar()
        tv_in_time.text = "$savedHour:$savedMinute"
    }
}