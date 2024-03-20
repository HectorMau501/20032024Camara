package com.example.a20032024camara

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts

class ActivityFoto : AppCompatActivity() {
    //Asociar
    private lateinit var foto: ImageView
    private lateinit var btnTomar: Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foto)
        //Asociar con instancia
        foto = findViewById(R.id.imgFoto)
        btnTomar = findViewById(R.id.btnTomar)

        //Metodos
        btnTomar.setOnClickListener {
            //Instancia para abrir la camara
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            //Lo que sucede cuando la camara regresa un resultado
            responseLauncher.launch(intent)
        }
    }

    //Variable que se ejecuta una vez que tome la foto
    private val responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){activityResult ->
        if(activityResult.resultCode == RESULT_OK){
            Toast.makeText(this,"Fotografia tomada",Toast.LENGTH_SHORT).show()
            val extras = activityResult.data!!.extras
            val imgBitmap = extras!!["data"] as Bitmap?
            foto.setImageBitmap(imgBitmap)
        }else{
            Toast.makeText(this,"Proceso cancelado",Toast.LENGTH_SHORT).show()
        }
    }//responseLauncher
}