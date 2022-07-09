package com.example.smartclass

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        val btn_login = findViewById<Button>(R.id.btn_login)
        val input_id = findViewById<EditText>(R.id.etid)
        val input_pswd = findViewById<EditText>(R.id.etpswd)
        btn_login.setOnClickListener {
            var id = input_id.text.toString()
            var pswd = input_pswd.text.toString()
            if (id == "admin" && pswd == "admin"){
                Toast.makeText(this,"Success Login",Toast.LENGTH_SHORT).show()
            }
            else{
                Toast.makeText(this,"Incorrect Id or Password",Toast.LENGTH_SHORT).show()
            }
        }

    }
}