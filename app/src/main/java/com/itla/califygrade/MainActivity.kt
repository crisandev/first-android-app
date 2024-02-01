package com.itla.califygrade

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnStudentInfo: Button = findViewById(R.id.btnStudentInfo)
        val btnCalcular: Button = findViewById(R.id.btnCalcular);
        val value: EditText = findViewById(R.id.textValues)

        btnStudentInfo.setOnClickListener{
            val Intent = Intent(this, InfoStudent::class.java)
            startActivity(Intent)
        }

        btnCalcular.setOnClickListener{
            showGrade(value.text.toString())
        }
    }

    private fun showGrade(value: String){
        calcularGrado(value)
    }

    private fun calcularGrado(value: String){
        try{
            var numberValue = value.toInt()
            correctInput(validateGrade(numberValue))
        }catch (e: NumberFormatException){
            incorrectInput(value);
        }
    }

    private fun validateGrade(numberValue: Int): String{
        if(numberValue > 100) return "Los valores deben de estar entre 0 y 100"
        else if(numberValue > 89) return "Tu nota es: A"
        else if(numberValue > 79) return "Tu nota es: B"
        else if(numberValue > 69) return "Tu nota es: C"
        return "Tu nota es: F"
    }

    private fun correctInput(value: String){
        val window = AlertDialog.Builder(this)
        window.setTitle("Grado")
            .setMessage(value)
            .setPositiveButton("OK") { dialog, _ ->
                dialog.dismiss()
            }
        val popup = window.create()
        popup.show()
    }

    private fun incorrectInput(value: String){
        val window = AlertDialog.Builder(this)
        window.setTitle("Error")
            .setMessage("No ingresaste ningún valor")
            .setPositiveButton("Ok") { dialog, _ ->
                dialog.dismiss()
            }
        val popup = window.create()
        popup.show()
    }
}