package com.example.comunicacionactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.comunicacionactivity.databinding.ActivityActividadCaso1Binding


class Actividad_Caso_1 : AppCompatActivity() {

    private lateinit var binding : ActivityActividadCaso1Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        crearObjetosDelXML()
    }

    private fun crearObjetosDelXML(){
        binding = ActivityActividadCaso1Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    /**
     * Volver
     * Eliminar la activity para volver a lo que le ha llamado
     *
     * @param[view]
     */
    fun volver(view: View){
        finish()
    }
}