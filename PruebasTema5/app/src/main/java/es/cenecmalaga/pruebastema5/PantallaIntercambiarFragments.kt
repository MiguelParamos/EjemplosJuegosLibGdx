package es.cenecmalaga.pruebastema5

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import es.cenecmalaga.pruebastema5.fragmentos.Fragmento2
import es.cenecmalaga.pruebastema5.fragmentos.Fragmento3
import es.cenecmalaga.pruebastema5.fragmentos.Fragmento4

class PantallaIntercambiarFragments : AppCompatActivity() {
    private val manager:FragmentManager by lazy { this.supportFragmentManager }
    private val fragmento2: Fragmento2 by lazy { Fragmento2() }
    private val fragmento3: Fragmento3 by lazy { Fragmento3() }
    private val fragmento4: Fragmento4 by lazy { Fragmento4() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.intercambiar_fragments)
    }

    fun verFragmento2(view: View) {
        var transaction:FragmentTransaction=manager.beginTransaction()
        transaction.replace(R.id.zonaFragments,fragmento2,"fragmento2")
        transaction.addToBackStack("fragmento2")
        transaction.commit()
    }

    fun verFragmento3(view: View) {
        var transaction:FragmentTransaction=manager.beginTransaction()
        transaction.replace(R.id.zonaFragments,fragmento3,"fragmento3")
        transaction.addToBackStack("fragmento3")
        transaction.commit()
    }

    fun verFragmento4(view: View) {
        var transaction:FragmentTransaction=manager.beginTransaction()
        transaction.replace(R.id.zonaFragments,fragmento4,"fragmento4")
        transaction.addToBackStack("fragmento4")
        transaction.commit()

    }

    fun quitarFragmento2(view: View) {
        var transaccion:FragmentTransaction=manager.beginTransaction();
        transaccion.remove(fragmento2)
        manager.popBackStackImmediate()
        transaccion.commit()
    }

}
