package com.example.pendaftaranwisuda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class MainActivity : AppCompatActivity() {

    private var signup_btn:Button? = null
    private var user_email_editText:EditText? = null
    private var user_password_editText:EditText?= null
    private var firebaseAuth:FirebaseAuth? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        signup_btn = findViewById(R.id.signup_btn)
        user_email_editText = findViewById(R.id.user_email)
        user_password_editText = findViewById(R.id.user_password)
        firebaseAuth = FirebaseAuth.getInstance()

        signup_btn?.setOnClickListener {
            //semua code untuk buat user baru
            RegisterNewUser()
        }
    }
    private fun RegisterNewUser (){
        var email_text = user_email_editText?.text.toString().trim()
        var password = user_password_editText?.text.toString().trim()

        //for profesional hahaha untuk mengecek apakah kosong yg di isi
        if (TextUtils.isEmpty(email_text) && TextUtils.isEmpty(password)){
            Toast.makeText(applicationContext,"This Field Cannot Be Empty", Toast.LENGTH_LONG).show()
        }
        else{
            firebaseAuth?.createUserWithEmailAndPassword(email_text,password)?.addOnCompleteListener(object : OnCompleteListener<AuthResult>{
                override fun onComplete(task: Task<AuthResult>) {
                    if(task.isSuccessful){
                        Toast.makeText(applicationContext, "Account Created", Toast.LENGTH_SHORT).show()
                        val user:FirebaseUser = firebaseAuth!!.currentUser!!

                        user.sendEmailVerification().addOnCompleteListener(object : OnCompleteListener<Void>{
                            override fun onComplete(task: Task<Void>) {
                                if(task.isSuccessful) {
                                    Toast.makeText(applicationContext, "Please Check Your Email For Verification", Toast.LENGTH_SHORT).show()
                                    startActivity(Intent(this@MainActivity, Login_activity::class.java))
                                }
                                else{
                                    val error = task.exception?.message
                                    Toast.makeText(applicationContext, "Error!!!"+error, Toast.LENGTH_LONG).show()
                                }
                            }
                        })
                    }
                    else{
                        val error = task.exception?.message
                        Toast.makeText(applicationContext, "Error!!!"+error, Toast.LENGTH_SHORT).show()
                    }
                }
            })
        }
    }
}