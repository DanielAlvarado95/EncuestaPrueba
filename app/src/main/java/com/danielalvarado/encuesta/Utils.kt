package com.danielalvarado.encuesta

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.danielalvarado.encuesta.view.DetailActivity
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.snackbar.Snackbar
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

fun Activity.toast(message: String, length: Int = Toast.LENGTH_SHORT) {
    Toast.makeText(this, message, length).show()
}//toast

fun mensaje(view: View, mensaje: String) {
    Snackbar.make(view, mensaje, Snackbar.LENGTH_SHORT)
        .show()
}//mensaje

fun Activity.showDialog() {
    MaterialAlertDialogBuilder(this).apply {
        setTitle(getString(R.string.atentcion))
        setMessage(R.string.volver)
        setCancelable(false)
        setNegativeButton(
            R.string.aceptar
        ) { dialog, which ->
            val intent = Intent(applicationContext, DetailActivity::class.java)
            //Va a iniciar el login activity como una nueva tarea
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
            startActivity(intent)
        }//dialog
            .show()
    }//apply
}//showDialog

@RequiresApi(Build.VERSION_CODES.O)
fun obtenerFecha():String {
    return  LocalDateTime.now()
        .format(DateTimeFormatter.ofPattern("MMM dd yyyy, hh:mm:ss a")).toString()
}