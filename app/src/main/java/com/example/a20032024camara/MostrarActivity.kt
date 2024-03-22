package com.example.a20032024camara

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast

class MostrarActivity : AppCompatActivity() {
    private lateinit var show: TextView
    private lateinit var editSeach: EditText
    private lateinit var btnSeach: Button
    private lateinit var codigoBarraArray: ArrayList<CodigoBarra?>//Para buscar por HashMap
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mostrar)

        editSeach = findViewById(R.id.editBuscar)
        btnSeach = findViewById(R.id.btnBuscar)
        show = findViewById(R.id.txtDatos)


        //Obtener los datos
        codigoBarraArray = intent.getSerializableExtra("codigoBarraArray") as ArrayList<CodigoBarra?>
        val stringBuilder = StringBuilder()

        for (codigoBarra in codigoBarraArray) { //Muestra solo los codigo que hay en el arreglo
            if (codigoBarra != null) {
                stringBuilder.append("Codigo: ${codigoBarra.code}\n")
            }
        }

        show.text = stringBuilder.toString() //Muestra

        //Boton de buscar con una variable que se asocia a la caja de buscar
        btnSeach.setOnClickListener {
            val searchCode = editSeach.text.toString()
            buscarCodigo(searchCode) //le pasamos por parametros buscar
        }
    }

    private fun buscarCodigo(searchCode: String) {
        var encontrado = false //Para cambar a true si es que se encontro
        val stringBuilder = StringBuilder()

        for (codigoBarra in codigoBarraArray) { //Itera entre el arreglo para buscar al     que buscamos
            if (codigoBarra != null && codigoBarra.code == searchCode) { //Compara para saber si es el que necesitamos
                stringBuilder.append("Codigo: ${codigoBarra.code}\n")
                stringBuilder.append("Descripcion: ${codigoBarra.description}\n")
                stringBuilder.append("Nombre: ${codigoBarra.nombre}\n")
                stringBuilder.append("Tipo de Sistema: ${codigoBarra.tipoQR}\n")

                encontrado = true
                break
            }
        }
        if(!encontrado){
            Toast.makeText(this, "No se encontro, intentelo de nuevo", Toast.LENGTH_LONG ).show()
        }

        show.text = stringBuilder.toString() //Muestra en el show

    }
}