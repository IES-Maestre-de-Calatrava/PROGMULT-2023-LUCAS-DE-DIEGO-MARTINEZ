package com.example.comunicacionactivity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts.StartActivityForResult
import com.example.comunicacionactivity.databinding.ActivityMainBinding

/**
 * Demostracion de apertura de activities desde otra activity
 *
 * 1)Apertura normal
 *
 * @author  LukitasDinamita
 */

class MainActivity : AppCompatActivity() {

    lateinit var activityResultLauncher: ActivityResultLauncher<Intent>
    private lateinit var binding:ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        crearObjetosDelXML()

        activityResultLauncher = registerForActivityResult(StartActivityForResult()) { result ->

            if (result.data != null) {
                val data: Intent = result.data!!
                val nombreDevuelto = data.getStringExtra("Mensaje")
                binding.textoDevuelto.text = nombreDevuelto
            }
        }

    }

    /**
     * Crear objetos partiendo del XML
     */

    private fun crearObjetosDelXML(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    /**
     *(1) Apertura normal
     * Abre una Activity concreta
     *
     * @param[view] objeto que llame al metodo
     */
    fun openSomeActivity(view: View){
        val intent = Intent(this,Actividad_Caso_1::class.java)
        startActivity(intent)
    }

    /**
     * (2) Apertura pasandole un valor
     * Abre una activity enviadnome datos
     *
     * @param [view] Objeto que llama al metodo
     */
    fun openSomeActivitySendingData(view: View){
        val intent = Intent(this,Recibe_Datos::class.java)

        intent.putExtra("DatosEnviados", binding.textoAEnviar.text.toString())
        intent.putExtra("Minoombre","lukilu")
        startActivity(intent)


    }

    /**
     *(3) Apertura con devolucion de datos
     */
    fun openSomeActivityForResult(view: View){
        val intent = Intent(this,activity_devuelve_datos::class.java)
        activityResultLauncher.launch(intent)
    }
}