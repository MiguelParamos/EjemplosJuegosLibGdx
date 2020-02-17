package es.cenecmalaga.pruebastema5

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import es.cenecmalaga.pruebastema5.fragmentos.Fragmento2
import es.cenecmalaga.pruebastema5.servicios.Servicio

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Creo fragment
        var frag2: Fragmento2 =
            Fragmento2()
        //Recupero FragmentManager que me permite insertar fragments en la actividad
        var manager:FragmentManager=this.supportFragmentManager
        //Empiezo la transacción para meter todos los fragment de golpe en la vista
        var transaccion:FragmentTransaction=manager.beginTransaction()
        transaccion.add(R.id.framelayout,frag2,"miFragmento2")
        transaccion.commit()
    }

    fun lanzarServicio(view: View) {
        var intent:Intent=
            Intent(this, Servicio::class.java)
        intent.putExtra("mensaje","mensaje al servicio")
        startService(intent);

    }

    fun quitarFragmento2(view: View) {
        //Recupero fragmentManager para poder obtener el fragment que quiero
        var manager:FragmentManager=this.supportFragmentManager
        //Recupero el fragment a través del manager
        var fragmento2: Fragment? =manager.findFragmentByTag("miFragmento2")
        //Empiezo la transacción
        var transaccion:FragmentTransaction=manager.beginTransaction();
        //Si no es nula, la elimino
        if(fragmento2!=null){
            transaccion.remove(fragmento2)
        }
        //Hago commit para que la transacción tenga efecto
        transaccion.commit()
    }

    fun irAIntercambioFragments(view: View) {
        var intent:Intent=Intent(this,PantallaIntercambiarFragments::class.java)
        startActivity(intent)
    }


}
