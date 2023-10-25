package com.example.toolbarapp20232024

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun mandarCorreo(){
        startActivity(
            Intent(Intent.ACTION_SEND).apply{
                type="text/plain"
                putExtra(Intent.EXTRA_SUBJECT,"Correo de prueba")
                putExtra(Intent.EXTRA_TEXT,"Hola, esto es un mensaje de prueba para la asignatura de ProgMult")
                putExtra(Intent.EXTRA_MAIL, arrayOf("lucasddiego@outlook.es"))
            }
        )

        fun lanzarAcercaDe(){
            Toast.makeText(this,"Aplicacion desarrollada por lukitas",Toast.LENGTH_LONG).show()
        }

        fun verMapa(){
            startActivity(
                Intent(Intent.ACTION_VIEW, Uri.parse("geo:38.9912307821621, -3.9206202133874215"))
                )
        }

        fun compartir(){
            startActivity(
                Intent(Intent.ACTION_SEND).apply {
                    type = "text/plain"
                    putExtra(Intent.EXTRA_TEXT,"Mira el sitio que he encontrado http://break4learning.weebly.com")
                }
            )
        }

    }
}