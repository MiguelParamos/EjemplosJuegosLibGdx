package es.cenecmalaga.pruebastema5.servicios

import android.app.Service
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.IBinder
import android.provider.MediaStore
import android.util.Log
import android.widget.Toast

class Servicio : Service() {

    lateinit private var player:MediaPlayer

    override fun onCreate() {
        super.onCreate()
    }


    override fun onBind(intent: Intent): IBinder {
        return onBind(intent);

    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {

        while(true){
            Log.d("Servicio",intent!!.getStringExtra("mensaje"))
            Thread.sleep(1000);
        }

        return Service.START_NOT_STICKY;
    }
}
