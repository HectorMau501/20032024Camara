package com.example.a20032024camara

import android.content.Intent
import android.graphics.Bitmap
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import java.util.ArrayList




class ActivityFoto : AppCompatActivity() {
    //Asociar
    private lateinit var foto: ImageView
    private lateinit var btnTomar: Button
    private var bitmapArray = ArrayList<Bitmap>()
    private var Index = 0 //Este es el indice actual de nuestro arreglo


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_foto)
        //Asociar con instancia
        foto = findViewById(R.id.imgFoto)
        btnTomar = findViewById(R.id.btnTomar)


        //Metodos
        btnTomar.setOnClickListener {
//            Instancia para abrir la camara
            val intent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
//            Lo que sucede cuando la camara regresa un resultado
            responseLauncher.launch(intent)
        }
    }

    fun onClick(v: View){
        when(v?.id){
            R.id.btnRetroceder ->{
                Regresar()
            }
            R.id.btnAdelantar ->{
                Adelantar()
            }
        }
    }//onClick

    private fun Regresar() {
            if(Index > 0){ //Verificamos que haya una imagen en el arreglo
                Index --//Disminuimos el indice
                foto.setImageBitmap(bitmapArray[Index])
            }else{
                Toast.makeText(this,"No hay imagenes anteriores",Toast.LENGTH_SHORT).show()
            }
    }

    private fun Adelantar() {
            if(Index < bitmapArray.size - 1){ //Verificamos que haya una imagen en el arreglo
                Index ++//Disminuimos el indice
                foto.setImageBitmap(bitmapArray[Index])
            }else{
                Toast.makeText(this,"No hay mas imagenes",Toast.LENGTH_SHORT).show()
            }
    }


    //Variable que se ejecuta una vez que tome la foto
    private val responseLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()){activityResult ->
        if(activityResult.resultCode == RESULT_OK){
            Toast.makeText(this,"Fotografia tomada",Toast.LENGTH_SHORT).show()
            val extras = activityResult.data?.extras
            val bitmap = extras?.get("data") as Bitmap?
            if(bitmap != null){
                bitmapArray.add(bitmap) //Agregamos a la lista de bitmapArray
                foto.setImageBitmap(bitmap) // Mostrar la ultima foto tomada
                Index = bitmapArray.size - 1 //Actualizamos el indice al ultimo elemento
            }else {
                Toast.makeText(this, "Error al obtener la imagen", Toast.LENGTH_SHORT).show()
            }
        }else{
            Toast.makeText(this,"Proceso cancelado",Toast.LENGTH_SHORT).show()
        }
    }//responseLauncher
}