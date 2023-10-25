package com.example.notificaciones

import android.Manifest
import android.app.Activity
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import com.example.notificaciones.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    //Identificador del canal que vamos a utilizar para las notificaciones
    //(1) CHANNEL
    private val CHANNEL_ID: String = "123"

    private var PETICION_PERMISOS: Int = 456

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        crearObjetosXML()

        createNotificationChannel()
    }

    private fun crearObjetosXML(){
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    //(2) CHANNEL
    private fun createNotificationChannel(){

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.O){
            //Declaracion del canal
            val name = "Mi Aplicacion"
            val descriptionText ="Canal para mis notificaciones"
            val importance = NotificationManager.IMPORTANCE_DEFAULT

            val channel = NotificationChannel(CHANNEL_ID, name,importance).apply {
                description = descriptionText
            }
            //Registra el canal en el sistema
            val notificationManager: NotificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

            notificationManager.createNotificationChannel(channel)
        }


    }

    fun lanzarNotificacion(view: View){

        val contentIntent = PendingIntent.getActivity(this,0,
            Intent(this,MainActivity::class.java),PendingIntent.FLAG_IMMUTABLE)

        //Creamos un Builder de notificaciones asociado a nuestro canal

        var notificationBuilder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.stat_sys_warning)
            .setContentTitle("Aviso")
            .setContentText("Esto es un aviso de notificacion")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(contentIntent)

        var notificationBuilder2 = NotificationCompat.Builder(this, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.stat_sys_warning)
            .setContentTitle("Hola")
            .setContentText("Esto es un atraco")
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setContentIntent(contentIntent)


        //Permissions
        //Verifica si ya le pregunte al usuario permisos y los denegó
        if(ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.POST_NOTIFICATIONS)) {
            Toast.makeText(this,"Tienes que darme permisos en ajustes",Toast.LENGTH_SHORT).show()
        }else {
            //Verificamos si se le ha concedido el permiso
            if (ActivityCompat.checkSelfPermission(
                    this,
                    Manifest.permission.POST_NOTIFICATIONS
                ) != PackageManager.PERMISSION_GRANTED
            ) {
                ActivityCompat.requestPermissions(
                    this,
                    arrayOf(
                        Manifest.permission.POST_NOTIFICATIONS
                    ), PETICION_PERMISOS
                )
            }
        }

        //Mostrar notificacion
        when(view.id){
            R.id.btnLanzaNotif ->{
                with(NotificationManagerCompat.from(this)) {
                    notify(1111, notificationBuilder.build())
                }
            }
            R.id.btnLanzarNotif2 -> {
                with(NotificationManagerCompat.from(this)) {
                    notify(1111, notificationBuilder2.build())
                }
            }
        }

    }
}