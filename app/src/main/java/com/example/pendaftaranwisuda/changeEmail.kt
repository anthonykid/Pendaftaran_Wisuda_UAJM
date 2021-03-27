package com.example.pendaftaranwisuda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.EmailAuthCredential
import com.google.firebase.auth.EmailAuthProvider
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser

class changeEmail : AppCompatActivity() {

    var user_email:EditText? = null
    var user_password:EditText? = null
    var new_email:EditText? = null
    var update:Button? = null
    var firebaseAuth:FirebaseAuth? = null
    var user:FirebaseUser? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_change_email)
        user_email = findViewById(R.id.email_edit_text_change)
        user_password = findViewById(R.id.password_edit_text_change)
        new_email = findViewById(R.id.new_email_edit_text_change)
        update = findViewById(R.id.update_btn)
        firebaseAuth = FirebaseAuth.getInstance()
        user = firebaseAuth?.currentUser

        update?.setOnClickListener {
            UpdateEmail()
        }
    }
    private fun UpdateEmail(){
        var email = user_email?.text.toString().trim()
        var password = user_password?.text.toString().trim()
        var new_email = new_email?.text.toString().trim()

        var userInfo = EmailAuthProvider.getCredential(email, password)
        user?.reauthenticate(userInfo)?.addOnCompleteListener(object : OnCompleteListener<Void>{
            override fun onComplete(task: Task<Void>) {
                user!!.updateEmail(new_email).addOnCompleteListener(object :OnCompleteListener<Void>{
                    override fun onComplete(task: Task<Void>) {
                        if (task.isSuccessful){
                            Toast.makeText(applicationContext, "Your Email Has Been Updated",Toast.LENGTH_SHORT).show()
                        }
                        else{
                            val error = task.exception?.message
                            Toast.makeText(applicationContext,"Error !!! " + error, Toast.LENGTH_SHORT).show()
                        }
                    }

                })
            }
        })
    }
}