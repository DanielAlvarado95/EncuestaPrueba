package com.danielalvarado.encuesta.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.commit
import androidx.fragment.app.replace
import com.danielalvarado.encuesta.R
import com.danielalvarado.encuesta.databinding.ActivityEncuestaBinding
import com.danielalvarado.encuesta.showDialog
import com.danielalvarado.encuesta.view.fragments.MultiplicacionFragment
import com.danielalvarado.encuesta.view.fragments.RestaFragment
import com.danielalvarado.encuesta.view.fragments.SumaFragment
import com.google.android.material.dialog.MaterialAlertDialogBuilder

class EncuestaActivity : AppCompatActivity() {
    private lateinit var binding: ActivityEncuestaBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEncuestaBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        val bundle = intent.extras
        val operacion = bundle!!.getString("operacion", "")
        seleccionarFragment(operacion)

        onBackPressedDispatcher.addCallback(this,onBackPressedCallback)
    }//onCreate

    private fun seleccionarFragment(operacion: String) {
        when(operacion){
            "suma" ->{
                supportFragmentManager.commit {
                    replace<SumaFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }//commit
            }//suma
            "resta" -> {
                supportFragmentManager.commit {
                    replace<RestaFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }//commit
            }//resta
            "multiplicacion" -> {
                supportFragmentManager.commit {
                    replace<MultiplicacionFragment>(R.id.frameContainer)
                    setReorderingAllowed(true)
                    addToBackStack("replacement")
                }//commit
            }//multiplicacion
        }//when
    }//seleccionarFragment


    private val onBackPressedCallback = object : OnBackPressedCallback(true) {
        override fun handleOnBackPressed() {
           showDialog()
        }//handleOnBackPressed
    }//onBackPressedCallback

}//EncuestaActivity

