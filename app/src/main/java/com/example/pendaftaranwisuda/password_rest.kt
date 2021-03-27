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
import com.google.firebase.auth.FirebaseAuth

class password_rest : AppCompatActivity() {

    private var user_email:EditText? = null
    private var button_reset: Button? = null
    private var firebase:FirebaseAuth? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_password_rest)

        user_email=findViewById(R.id.user_email_reset)
        button_reset=findViewById(R.id.reset_btn)
        firebase= FirebaseAuth.getInstance()

        button_reset?.setOnClickListener {
            ResetPassword()
        }

    }
    private fun ResetPassword(){
        var email = user_email?.text.toString().trim()

        if (TextUtils.isEmpty(email)){
            Toast.makeText(applicationContext,"Please Input Your Email", Toast.LENGTH_LONG).show()
        }
        else{
            firebase?.sendPasswordResetEmail(email)?.addOnCompleteListener(object : OnCompleteListener<Void>{
                override fun onComplete(task: Task<Void>) {
                    if (task.isSuccessful){
                        Toast.makeText(applicationContext,"Please Check Your Email For Reset", Toast.LENGTH_LONG).show()
                    }
                    else{
                        val error = task.exception?.message
                        Toast.makeText(applicationContext,"Error"+error, Toast.LENGTH_LONG).show()
                    }
                }
            })
        }
    }
    fun LogIn1(view: View) {
        startActivity(Intent(this@password_rest, Login_activity::class.java))
    }
}