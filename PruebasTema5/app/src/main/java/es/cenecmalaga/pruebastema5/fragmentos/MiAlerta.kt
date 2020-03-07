package es.cenecmalaga.pruebastema5.fragmentos

import android.app.AlertDialog
import android.app.Dialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import es.cenecmalaga.pruebastema5.MainActivity
import es.cenecmalaga.pruebastema5.R

class MiAlerta: DialogFragment() {
    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
       val builder:AlertDialog.Builder=AlertDialog.Builder(activity,R.style.EstiloAlerta)
        builder.setMessage(R.string.mensajeDialogo1)
        builder.setTitle(R.string.tituloDialogo1)
        builder.setPositiveButton("Me gusta",
            (object: DialogInterface.OnClickListener{
            override fun onClick(dialog: DialogInterface?, which: Int) {
                dialog!!.dismiss()
                (activity as MainActivity).pressBack()
            }

        }))
        return builder.create()
    }
}