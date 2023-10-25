package com.example.menucontextual20232024

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ContextMenu
import android.view.MenuItem
import android.view.View
import com.example.menucontextual20232024.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crearObjetosXML()

        registerForContextMenu(binding.editTextIdiomas)
        registerForContextMenu(binding.editTextOficios)
    }

    private fun crearObjetosXML(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onCreateContextMenu(menu: ContextMenu,v: View, menuInfo: ContextMenu.ContextMenuInfo?){
        super.onCreateContextMenu(menu, v, menuInfo)
        when(v){
            binding.editTextIdiomas -> menuInflater.inflate(R.menu.menu_idiomas, menu)
            binding.editTextOficios -> menuInflater.inflate(R.menu.menu_oficios, menu)
        }
    }

    override fun onContextItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.ingles ->{
                binding.editTextIdiomas.setText("Inglés")
                true
            }
            R.id.frances ->{
                binding.editTextIdiomas.setText("Francés")
                true
            }
            R.id.aleman ->{
                binding.editTextIdiomas.setText("Alemán")
                true
            }
            R.id.informatica ->{
                binding.editTextOficios.setText("Informática")
                true
            }
            R.id.comercial ->{
                binding.editTextOficios.setText("Comercial")
                true
            }
            R.id.administrativo ->{
                binding.editTextOficios.setText("Administrativo")
                true
            }
            else -> super.onContextItemSelected(item)
        }
    }

}