package com.example.comunicacionactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comunicacionactivity.databinding.ActivityRecibeDatosBinding

class Recibe_Datos : AppCompatActivity() {

    private lateinit var binding: ActivityRecibeDatosBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crearObjetosDelXML()

        //Declara un objeto para el intent que recibe en la llamada

        val objetoIntent: Intent = intent

        var datosEnviados = objetoIntent.getStringExtra("DatosEnviados")

        binding.textView2.text = datosEnviados
    }

    private fun crearObjetosDelXML(){
        binding = ActivityRecibeDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }






}