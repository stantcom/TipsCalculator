package com.example.tipscalculator

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tipscalculator.databinding.ActivityMainBinding
import java.text.NumberFormat
import kotlin.math.ceil

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.calculateButton.setOnClickListener{ calculateTip() }

        //Bindear para que te de el resultado

    }
    //Funcion de calculo de propinas
    private fun calculateTip() {
        //Variable en la cual se le asigna el bindeo de la vista que recibe encadenamiento .text para pasarlo a string
        val stringInTextField = binding.costOfService.text.toString()
        // Basicamente pasamos el string de lo que pone el usuario a tipo Double con coma flotante
        val cost = stringInTextField.toDoubleOrNull()
        if (cost == null){
            binding.tipResult.text = ""
            return
        }

        // Variable la cual ponemos el Radiogroup y le ponemos atributo que si esta chekeado haga X funcion


        //  val selectedID = binding.tipOptions.checkedRadioButtonId
        //
        //        val tipPercentage = when (selectedID) {
        //            R.id.option_twenty_percent -> 0.20
        //            R.id.option_eighteen_percent ->0.18
        //            else -> 0.15

        val tipPercentage = when (binding.tipOptions.checkedRadioButtonId) {
            R.id.option_twenty_percent -> 0.20
            R.id.option_eighteen_percent ->0.18
            else -> 0.15
        }
        //variable la cual calcula el procentaje anterior por la variable cost
        var tip = tipPercentage * cost!!


        //variable de redodnear si esta el switch chekeado
        val roundUp = binding.roundUpSwitch.isChecked
        //una funcion de lso duende que hacen redodnear para arriba nose
        if (roundUp) {
            tip = kotlin.math.ceil(tip)
        }
        //Formato para distintos tipos de monedas
        val formattedTip = NumberFormat.getCurrencyInstance().format(tip)

        //Bindear para que te de el resultado
        binding.tipResult.text = getString(R.string.tip_amount, formattedTip)
    }


}



