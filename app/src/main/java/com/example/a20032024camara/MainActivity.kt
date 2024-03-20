package com.example.a20032024camara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val lector = findViewById<Button>(R.id.btnLector)
        val camara = findViewById<Button>(R.id.btnFoto)

        lector.setOnClickListener { lector() }
        camara.setOnClickListener { camara() }
    }//Oncreate

    fun lector(){
        val lectorAct = Intent(applicationContext, ActivityLector::class.java)
        startActivity(lectorAct)
    }
    fun camara(){
        val fotoAct = Intent(applicationContext, ActivityFoto::class.java)
        startActivity(fotoAct)
    }
}