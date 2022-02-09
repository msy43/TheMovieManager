package com.msy.themoviemanager

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun toMainActivity2(){
        val intent = Intent(this,MainActivity2::class.java)
        startActivity(intent)
        finish()
    }
}