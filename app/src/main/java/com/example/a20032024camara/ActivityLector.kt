package com.example.a20032024camara

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.zxing.integration.android.IntentIntegrator

class ActivityLector : AppCompatActivity() {

    //Insrancias
    private lateinit var codigo: EditText
    private lateinit var descripcion: EditText
    private lateinit var btnEscanear: Button
    private lateinit var btnCapturar: Button
    private lateinit var btnLimpiar: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lector)

        //Asociar con componentes grafico
        codigo = findViewById(R.id.editCodigo)
        descripcion= findViewById(R.id.editDescripcion)
        btnEscanear = findViewById(R.id.btnEscanear)
        btnCapturar = findViewById(R.id.btnCapturar)
        btnLimpiar= findViewById(R.id.btnLimpiar)

        //Eventos
        btnEscanear.setOnClickListener { escanearCodigo() }
        btnCapturar.setOnClickListener {
            if(codigo.text.toString().isNotEmpty() && descripcion.toString().isNotEmpty()){
                Toast.makeText(this, "Datos capturados",Toast.LENGTH_SHORT).show()
                limpiar()
            } else{
                Toast.makeText(this, "Debe registrar datos", Toast.LENGTH_LONG ).show()
            }
        }//btnCapturar
        btnLimpiar.setOnClickListener { limpiar() }
    }

    private fun escanearCodigo() {
        //Instancia para leer codigos
        val intentIntegrator = IntentIntegrator(this@ActivityLector)
        //Definir el tipo de codigo a leer cualquier formato de codigo
        intentIntegrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES)
        intentIntegrator.setPrompt("Lector de codigos")//Titulo en camara
        intentIntegrator.setCameraId(0)//Defini camara frontal si fuera la que esta hacia el otro lado es 1
        intentIntegrator.setBeepEnabled(true)//emitir beep al tomar la foto
        intentIntegrator.setBarcodeImageEnabled(true) //almacenar el codigo leido
        intentIntegrator.initiateScan() //iniciar escaneo
    }//escaneoCodigo

    //Metodo para determinar que hacer despues de leer el codigo
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        //Instancias para recibir el resultado (lectura de codigo)
        val intentResult = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        //Validar que no este vacia
        if(intentResult != null){
            //Validar leyo no este vacia
            if(intentResult.contents == null){
                //Mensaje informativo
                Toast.makeText(this, "Codigo leido", Toast.LENGTH_SHORT).show()
            }else{
                //Mensaje informativo - si hubo datos
                Toast.makeText(this, "Codigo leido", Toast.LENGTH_SHORT).show()
                //Colocar el codigo en la caja de texto
                codigo.setText(intentResult.contents)
            }//if-else ==null
        }else{
            super.onActivityResult(requestCode, resultCode, data)
        }//if-else != null
    }//onActivityResult

    private fun limpiar() {
        codigo.setText("")
        descripcion.setText("")
        codigo.requestFocus()
    }//Limpiar
}