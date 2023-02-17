package edu.iest.androidwear

import android.app.Activity
import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Toast
import edu.iest.androidwear.databinding.ActivityRelojBinding

class MainActivity : Activity(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: ActivityRelojBinding
    private var textoSeleccionado: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityRelojBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvSaludo.text = "Escoge 2 Números:"
        binding.bnCambio.text = "Enviar"

        val adaptador = ArrayAdapter.createFromResource(
            this,
            R.array.numeros, android.R.layout.simple_spinner_item
        )
        adaptador.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)


        binding.spN1.adapter = adaptador
        binding.spN1.onItemSelectedListener = this

        val adaptador2= ArrayAdapter.createFromResource(
            this,
            R.array.numeros, android.R.layout.simple_spinner_item
        )
        adaptador2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spN2.adapter = adaptador2
        binding.spN2.onItemSelectedListener = this


        binding.bnCambio.setOnClickListener {
            val alerta = AlertDialog.Builder(this)
            alerta.setTitle("Atencion")
                .setMessage("Quiere enviar el numero $textoSeleccionado")
                .setCancelable(false)
                .setPositiveButton("OK",
                    DialogInterface.OnClickListener { dialogInterface, i ->
                        //aqui el codigo
                        binding.tvSaludo.text = ("El numero mayor es $textoSeleccionado")
                    })
                .setNegativeButton(
                    "Cancelar",
                    DialogInterface.OnClickListener() { dialogInterface, i ->
                        Toast.makeText(
                            this,
                            "Una lastima :(", Toast.LENGTH_SHORT
                        ).show()
                    })
                .show()
        }

    }

    override fun onItemSelected(
        vistaPadre: AdapterView<*>?,
        vistaRow: View?,
        posicion: Int,
        idVista: Long
    ) {
        textoSeleccionado =
            vistaPadre?.getItemAtPosition(posicion).toString()
        /*Toast.makeText(
            this, "Elegiste $textoSeleccionado",
            Toast.LENGTH_LONG
        ).show()*/
    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
    }
}