package es.cenecmalaga.pruebastema5.fragmentos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.SeekBar
import android.widget.Spinner
import android.widget.Toast
import androidx.fragment.app.Fragment
import es.cenecmalaga.pruebastema5.MainActivity
import es.cenecmalaga.pruebastema5.R

public class Fragmento1 : Fragment() {
    private lateinit var  layoutInflado:ViewGroup //Layout de mi propio fragment que cojo en onCreateView

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        //Ahora que tengo el layout inflado y recogido en la variable, obtengo el seekbar a partir de el
        //Y llamo en su onclick a la función pertinente de la actividad
        super.onActivityCreated(savedInstanceState)
        val seekBar: SeekBar =layoutInflado.findViewById(R.id.barraColor)
        seekBar.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
            override fun onStartTrackingTouch(seekBar: SeekBar?) {

            }

            override fun onStopTrackingTouch(seekBar: SeekBar?) {
               }

            override fun onProgressChanged(seekBar: SeekBar?, progress: Int, fromUser: Boolean) {
                Toast.makeText(activity,"Valor SeekBar: "+seekBar!!.progress,Toast.LENGTH_LONG).show()
                (activity as MainActivity).colorAImagen(seekBar!!.progress)
            }
        }
        )
          }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //Inflo el layout del fragment en la variable
        this.layoutInflado=inflater.inflate(R.layout.layout_fragment,container) as ViewGroup
        //Lo devuelvo
        return layoutInflado
    }

}