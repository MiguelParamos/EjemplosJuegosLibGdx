package es.cenecmalaga.pruebastema5

import android.content.DialogInterface
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.core.widget.ImageViewCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import es.cenecmalaga.pruebastema5.fragmentos.Fragmento2
import es.cenecmalaga.pruebastema5.fragmentos.MiAlerta
import es.cenecmalaga.pruebastema5.servicios.Servicio
import kotlinx.android.synthetic.main.activity_main.view.*

class MainActivity : AppCompatActivity()  {
   private  val imagen: ImageView by lazy{findViewById<ImageView>(R.id.imagenTint)}
    val layoutFondo:FrameLayout by lazy { findViewById<FrameLayout>(R.id.layoutFondo) }
    val fragmentManager:FragmentManager by lazy{this.supportFragmentManager}

     fun colorAImagen(c: Int) {
        imagen.setColorFilter(Color.argb(255,c,0,0));
        layoutFondo.setBackgroundColor(Color.argb(255,c,0,0));
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //Creo fragment
        var frag2: Fragmento2 =
            Fragmento2()

        //Empiezo la transacción para meter todos los fragment de golpe en la vista
        var transaccion:FragmentTransaction=fragmentManager.beginTransaction()
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
        val fm:FragmentManager=this.supportFragmentManager
        val miAlerta:MiAlerta= MiAlerta()
        miAlerta.show(fm,"alerta")
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



    fun Color100(view: View) {
        Toast.makeText(this,"Cambiando color a 100",Toast.LENGTH_LONG).show()
        colorAImagen(100)
    }

    override fun onBackPressed() {
        //super.onBackPressed()
        var builder:AlertDialog.Builder=AlertDialog.Builder(this);
        builder.setTitle(R.string.irAtras);
        builder.setMessage(R.string.irAtras);
        val thisRef:MainActivity=this;
        builder.setPositiveButton("Me gusta",
            (object: DialogInterface.OnClickListener{
                override fun onClick(dialog: DialogInterface?, which: Int) {
                    dialog!!.dismiss()
                    thisRef.pressBack()
                }

            }))
       builder.create().show();
    }

    fun pressBack(){
        super.onBackPressed()
    }

}
