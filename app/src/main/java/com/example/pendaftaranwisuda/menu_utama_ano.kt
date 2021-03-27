package com.example.pendaftaranwisuda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class menu_utama_ano : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_menu_utama_ano)
    }
    fun LogIn(view: View) {
        startActivity(Intent(this@menu_utama_ano, Login_activity::class.java))
    }
    fun SignUp (view: View){
        startActivity(Intent(this@menu_utama_ano, MainActivity::class.java))
    }
}