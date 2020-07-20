package com.example.quiz

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {

    var quizbutton:Button?=null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

       quizbutton=findViewById(R.id.button_startquiz)
        quizbutton!!.setOnClickListener {
            var intent= Intent(this@MainActivity,SafetyActivity::class.java)
            startActivity(intent)
        }
    }
}