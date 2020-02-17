package es.cenecmalaga.pruebastema5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun lanzarServicio(view: View) {
        var intent:Intent=
            Intent(this,Servicio::class.java)
        intent.putExtra("mensaje","mensaje al servicio")
        startService(intent);

    }


}
