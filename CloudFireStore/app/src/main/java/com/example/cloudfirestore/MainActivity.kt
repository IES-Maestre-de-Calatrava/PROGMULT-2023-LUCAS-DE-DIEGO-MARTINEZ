package com.example.cloudfirestore

import android.location.GnssAntennaInfo.Listener
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.example.cloudfirestore.databinding.ActivityMainBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ListenerRegistration

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding

    //Necesario para conectar a Firebase
    private val db=FirebaseFirestore.getInstance()
    private val myCollection = db.collection("empresas")

    //necesario para visualizacion en tiempo real
    private lateinit var registration: ListenerRegistration
    private var escuchando: Boolean= false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crearObjetosDelXml()

        binding.buttonGuardar.setOnClickListener{
            guardarRegistro()
        }

        binding.buttonCargar.setOnClickListener {
            cargarDet()
        }

        binding.buttonEliminar.setOnClickListener {
            eliminarRegistro()
        }

        binding.buttonTraerTodos.setOnClickListener {
            listarTodos()
        }

        binding.buttonFiltrarPorCiudad.setOnClickListener {
            listarConFiltro()
        }

        binding.buttonTiempoReal.setOnClickListener {
            activarTiempoReal()
        }
    }

    /**
     * Activa la actualizacion en tiempo real
     */

    private fun activarTiempoReal(){
        val docRef = db.collection("empresas").document("123")
        var TAG = "firebase-db"
        if (this.escuchando){
            registration.remove()
            this.escuchando=false
        } else {
            registration= docRef.addSnapshotListener(){
                snapshot,
                    e->if(e!=null){
                        return@addSnapshotListener
                    }
                if (snapshot!=null && snapshot.exists()){
                    binding.editTextNombre.setText(snapshot.data?.get("nombre").toString())
                    binding.editTextDireccion.setText(snapshot.data?.get("direccion").toString())
                } else {
                    Log.d(TAG,"Datos a null")
                }
            }
            this.escuchando=true
        }
    }

    /**
     * Guardar registro
     *
     * Si el registro con el identificador sumininistrado
     * no exite entonces lo crea.
     * Si existe, modifica los datos
     */
    private fun guardarRegistro(){
        myCollection.document(binding.editTextNif.text.toString()).set(
            hashMapOf(
                "nombre" to binding.editTextNombre.text.toString(),
                "direccion" to binding.editTextDireccion.text.toString()
            )
        )
        resultadoOperacion("Registro guardado correctamente")
    }

    private fun resultadoOperacion(mensaje:String){
        binding.editTextNif.setText("")
        binding.editTextNombre.setText("")
        binding.editTextDireccion.setText("")
        Toast.makeText(this,mensaje, Toast.LENGTH_LONG).show()
    }

    private fun cargarDet(){
        myCollection.document(binding.editTextNif.text.toString())
            .get()
            .addOnSuccessListener {
                if(it.exists()){
                    binding.editTextNombre.setText(it.get("nombre").toString())
                    binding.editTextDireccion.setText(it.get("direccion").toString())
                } else {
                    resultadoOperacion("No existe una empresa con ese nif")
                }
            }
    }

    private fun eliminarRegistro(){
        myCollection.document(binding.editTextNif.text.toString())
            .delete()
        resultadoOperacion("Registro eliminado correctamente")
    }

    private fun crearObjetosDelXml(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun listarTodos(){
        myCollection
            .get()
            .addOnSuccessListener {
                resultado->
                    binding.textViewLista.setText("")
                    for ( documento in resultado ){
                        binding.textViewLista.append(documento.get("nombre").toString()+ " "+
                        documento.get("direccion").toString() + "\n")
                    }
            }
    }

    private fun listarConFiltro(){
        myCollection
            .whereEqualTo("direccion",binding.editTextDireccion.text.toString())
            .get()
            .addOnSuccessListener {
                    resultado->
                binding.textViewLista.setText("")
                for ( documento in resultado ){
                    binding.textViewLista.append(documento.get("nombre").toString()+ " "+
                            documento.get("direccion").toString() + "\n")
                }
            }

    }
}