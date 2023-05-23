package com.danielalvarado.encuesta.view.fragments

import android.app.AlertDialog
import android.content.Intent
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import androidx.annotation.RequiresApi
import androidx.lifecycle.lifecycleScope
import androidx.room.Room
import com.danielalvarado.encuesta.R
import com.danielalvarado.encuesta.data.database.DBPrueba
import com.danielalvarado.encuesta.data.model.Resultados
import com.danielalvarado.encuesta.databinding.FragmentRestaBinding
import com.danielalvarado.encuesta.mensaje
import com.danielalvarado.encuesta.obtenerFecha
import com.danielalvarado.encuesta.view.GraficasActivity
import kotlinx.coroutines.launch


class RestaFragment : Fragment() {
    private lateinit var binding: FragmentRestaBinding

    val items = listOf("10", "20", "30", "40", "50", "60")
    lateinit var room: DBPrueba
    lateinit var res: Resultados
    @RequiresApi(Build.VERSION_CODES.O)
    val fecha = obtenerFecha()
    var resultado1 = 0
    var resultado2 = 0


    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRestaBinding.inflate(inflater, container, false)
        cargarSpinner()
        buttonConfirm()
        room = Room.databaseBuilder(requireContext(), DBPrueba::class.java, "dbPruebas").build()
        return binding.root
    }

    private fun cargarSpinner() {
        val adapter = ArrayAdapter(
            requireActivity(),
            com.google.android.material.R.layout.support_simple_spinner_dropdown_item,
            items
        )
        binding.actSuma1.setAdapter(adapter)
        binding.actSuma2.setAdapter(adapter)
        binding.actSuma3.setAdapter(adapter)
        binding.actSuma4.setAdapter(adapter)
        binding.actSuma5.setAdapter(adapter)
    }//cargarSpinner

    @RequiresApi(Build.VERSION_CODES.O)
    private fun buttonConfirm() {
        binding.btnConfirm.setOnClickListener {
            if (binding.actSuma1.text.isEmpty()
                || binding.actSuma2.text.isEmpty()
                || binding.actSuma3.text.isEmpty()
                || binding.actSuma4.text.isEmpty()
                || binding.actSuma5.text.isEmpty()) {
                mensaje(requireView(),"No se pueden dejar campos vacios")
            }else{
                dialogoConfirmacion()
            }//else
        }//onCLick
    }//buttonConfirm

    @RequiresApi(Build.VERSION_CODES.O)
    private fun dialogoConfirmacion(){
        val view = View.inflate(context, R.layout.dialog_confirmacion, null)
        val builder = AlertDialog.Builder(context)
        builder.setView(view)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.show()
        dialog.window?.setBackgroundDrawableResource(android.R.color.transparent)
        val btnConfrim = view.findViewById<Button>(R.id.btn_confirm)
        val btnCancelar = view.findViewById<Button>(R.id.btn_cancel)
        btnConfrim.setOnClickListener {
            obtenerResultados()
            dialog.dismiss()
        }//btnConfirm
        btnCancelar.setOnClickListener {
            dialog.dismiss()
        }//btnCancelar
    }//dialogoConfrimacion

    @RequiresApi(Build.VERSION_CODES.O)
    private fun obtenerResultados(){
        if (binding.actSuma1.text.toString().toInt() == 20){
            resultado1 += 5
        }
        if (binding.actSuma2.text.toString().toInt() == 10){
            resultado1 += 5
        }
        if (binding.actSuma3.text.toString().toInt() == 30){
            resultado2 += 5
        }
        if (binding.actSuma4.text.toString().toInt() == 30){
            resultado2 += 5
        }
        if (binding.actSuma5.text.toString().toInt() == 30){
            resultado2 += 5
        }
        res = Resultados(
            obtenerFecha(),
            fecha
        )
        agregarResultados(room,res)
    }//obtenerResultados

    private fun lanzarActividad() {
        val bundle = Bundle()
        val editProfileIntent = Intent(getActivity(), GraficasActivity::class.java)
        bundle.putString("operacion", "Restas")
        bundle.putString("res1", resultado1.toString())
        bundle.putString("res2", resultado2.toString())
        editProfileIntent.putExtras(bundle)
        startActivity(editProfileIntent)
        requireActivity().run {
            finish()
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun agregarResultados(room: DBPrueba, usuario: Resultados){
        lifecycleScope.launch {
            room.daoResultados().agregarResultado(usuario)
        }//launch
        lanzarActividad()
    }//agregarResultados

}