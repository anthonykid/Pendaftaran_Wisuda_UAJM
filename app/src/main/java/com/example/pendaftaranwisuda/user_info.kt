package com.example.pendaftaranwisuda

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.*

class user_info : AppCompatActivity() {

    var fullName:TextView? = null
    var stambuk:TextView? = null
    var fakultas:TextView? = null
    var jurusan:TextView? = null
    var judulSkirpsi:TextView? = null
    var tempatTanggalLahir:TextView? = null
    var whatsapp:TextView? = null
    var jenisKelamin:TextView? = null

    var firebaseAuth: FirebaseAuth?=null
    var firebaseDatabase: DatabaseReference?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_info)

        fullName=findViewById(R.id.fullName_textview)
        stambuk=findViewById(R.id.stambuk_textview)
        fakultas=findViewById(R.id.fakultas_textview)
        jurusan=findViewById(R.id.jurusan_textview)
        judulSkirpsi=findViewById(R.id.judulSkripsi_textview)
        tempatTanggalLahir=findViewById(R.id.tempatTanggalLahir_textview)
        whatsapp=findViewById(R.id.whatsapp_textview)
        jenisKelamin=findViewById(R.id.jenisKelamin_textview)

        firebaseAuth=FirebaseAuth.getInstance()
        firebaseDatabase= FirebaseDatabase.getInstance().reference.child("Users").child(firebaseAuth?.currentUser!!.uid)

        firebaseDatabase?.addValueEventListener(object : ValueEventListener{
            override fun onCancelled(p0: DatabaseError) {

            }
            override fun onDataChange(task: DataSnapshot) {
                if (task.exists()){
                    val fullnamemhs = task.child("FullName").value as String
                    val stambukmhs = task.child("Stambuk").value as String
                    val fakultasmhs = task.child("Fakultas").value as String
                    val jurusanmhs = task.child("Jurusan").value as String
                    val judulskripsimhs = task.child("JudulSkripsi").value as String
                    val tempattanggallahirmhs = task.child("Tempat Tanggal Lahir").value as String
                    val whatsappmhs = task.child("Whatsapp").value as String
                    val jeniskelaminmhs = task.child("Jenis Kelamin").value as String

                    fullName?.text = fullnamemhs
                    stambuk?.text = stambukmhs
                    fakultas?.text = fakultasmhs
                    jurusan?.text = jurusanmhs
                    judulSkirpsi?.text = judulskripsimhs
                    tempatTanggalLahir?.text = tempattanggallahirmhs
                    whatsapp?.text = whatsappmhs
                    jenisKelamin?.text = jeniskelaminmhs

                }
            }

        })
    }
    fun update(view: View) {
        startActivity(Intent(this@user_info, profile_activity::class.java))
    }
    fun changeEmail(view: View) {
        startActivity(Intent(this@user_info, changeEmail::class.java))
    }
}