package com.example.pendaftaranwisuda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils
import android.view.View
import android.widget.*
import com.google.android.gms.tasks.OnCompleteListener
import com.google.android.gms.tasks.Task
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase

class profile_activity : AppCompatActivity() {

    var user_fullName:EditText?=null
    var user_stambuk:EditText?=null
    var user_fakultas:EditText?=null
    var user_jurusan:EditText?=null
    var user_skripsi:EditText?=null
    var user_ttl:EditText?=null
    var user_whatsapp:EditText?=null
    var user_jeniskelamin:TextView?=null

    var dtor: RadioButton? = null
    var rtod: RadioButton? = null

    var update_btn:Button?=null
    var firebaseAuth:FirebaseAuth?=null
    var firebaseDatabase:DatabaseReference?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_profile_activity)

        user_fullName=findViewById(R.id.user_profile_name)
        user_stambuk=findViewById(R.id.user_profile_stambuk)
        user_fakultas=findViewById(R.id.user_profile_fakultas)
        user_jurusan=findViewById(R.id.user_profile_jurusan)
        user_skripsi=findViewById(R.id.user_profile_judul_skripsi)
        user_ttl=findViewById(R.id.user_profile_ttl)
        user_whatsapp=findViewById(R.id.user_profile_whattsap)

        dtor=findViewById(R.id.dtor) as RadioButton
        dtor!!.isChecked = true
        rtod=findViewById(R.id.rtod) as RadioButton
        update_btn=findViewById(R.id.userinfo_update_btn)
        firebaseAuth=FirebaseAuth.getInstance()
        firebaseDatabase=FirebaseDatabase.getInstance().reference.child("Users").child(firebaseAuth?.currentUser!!.uid)

        update_btn?.setOnClickListener {
            GenderInfo()
            SaveUserInfo()

        }
    }
        private fun GenderInfo(){
            if (dtor!!.isChecked) {
                user_jeniskelamin = dtor
            }
            if (rtod!!.isChecked) {
                user_jeniskelamin = rtod
            }
        }

    private fun SaveUserInfo(){
        val fullname = user_fullName?.text.toString().trim()
        val stambuk = user_stambuk?.text.toString().trim()
        val fakultas = user_fakultas?.text.toString().trim()
        val jurusan = user_jurusan?.text.toString().trim()
        val skripsi = user_skripsi?.text.toString().trim()
        val ttl = user_ttl?.text.toString().trim()
        val whatsapp = user_whatsapp?.text.toString().trim()
        val jeniskelamin = user_jeniskelamin?.text.toString().trim()

        if (TextUtils.isEmpty(fullname)){
            Toast.makeText(applicationContext, "Harap Isi Nama Lengkap Anda",Toast.LENGTH_SHORT).show()
        }
        else if (TextUtils.isEmpty(stambuk)){
            Toast.makeText(applicationContext, "Harap Mengisi Stambuk Anda",Toast.LENGTH_SHORT).show()
        }
        else if (TextUtils.isEmpty(fakultas)){
            Toast.makeText(applicationContext, "Harap Mengisi Fakultas Anda",Toast.LENGTH_SHORT).show()
        }
        else if (TextUtils.isEmpty(jurusan)){
            Toast.makeText(applicationContext, "Harap Mengisi Jurusan Anda",Toast.LENGTH_SHORT).show()
        }
        else if (TextUtils.isEmpty(skripsi)){
            Toast.makeText(applicationContext, "Harap Mengisi Judul Skripsi Anda",Toast.LENGTH_SHORT).show()
        }
        else if (TextUtils.isEmpty(ttl)){
            Toast.makeText(applicationContext, "Harap Mengisi Tempat dan Tanggal Lahir Anda",Toast.LENGTH_SHORT).show()
        }
        else if (TextUtils.isEmpty(whatsapp)){
            Toast.makeText(applicationContext, "Harap Mengisi Nomor Whatsapp Anda",Toast.LENGTH_SHORT).show()
        }
        else{
            val userinfo = HashMap<String,Any>()
            userinfo.put("FullName",fullname)
            userinfo.put("Stambuk",stambuk)
            userinfo.put("Fakultas",fakultas)
            userinfo.put("Jurusan",jurusan)
            userinfo.put("JudulSkripsi",skripsi)
            userinfo.put("Tempat Tanggal Lahir",ttl)
            userinfo.put("Whatsapp",whatsapp)
            userinfo.put("Jenis Kelamin", jeniskelamin)

                firebaseDatabase?.updateChildren(userinfo)?.addOnCompleteListener(object : OnCompleteListener<Void>{
                override fun onComplete(task: Task<Void>) {
                    if(task.isSuccessful){
                        Toast.makeText(applicationContext, "Your Information Has Been Updated",Toast.LENGTH_SHORT).show()
                    }
                    else{
                        val error = task.exception?.message
                        Toast.makeText(applicationContext,"Error!! "+error, Toast.LENGTH_SHORT).show()
                    }
                }

            })
        }
    }
}