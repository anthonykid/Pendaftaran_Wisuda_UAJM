package com.example.pendaftaranwisuda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class TestActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test)
    }
    fun viewInput (view: View){
        startActivity(Intent(this@TestActivity, user_info :: class.java))
    }
    fun Profile (view: View){
        startActivity(Intent(this@TestActivity, profile_activity :: class.java))
    }
    fun changeEmail(view: View) {
        startActivity(Intent(this@TestActivity, changeEmail::class.java))
    }
}