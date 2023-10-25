package com.example.comunicacionactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.comunicacionactivity.databinding.ActivityDevuelveDatosBinding
import com.example.comunicacionactivity.databinding.ActivityMainBinding

class activity_devuelve_datos : AppCompatActivity() {

    private lateinit var binding: ActivityDevuelveDatosBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crearObjetosDelXML()
    }


    /**
     * Elimina la activity para volver a la que ha llamado
     */
    fun volver (view:View){

        val intent = Intent()

        intent.putExtra("Mensaje",binding.editTexttextoEnviarDevuelve.text.toString())

        setResult(132,intent)

        finish()
    }
    private fun crearObjetosDelXML(){
        binding = ActivityDevuelveDatosBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}