package com.example.comunicacionactivity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.comunicacionactivity.databinding.ActivityMainBinding

class actividad_caso2 : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        crearObjetosDelXML()
    }

    private fun crearObjetosDelXML(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

}