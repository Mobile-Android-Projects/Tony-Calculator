package com.example.tonycalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import com.example.tonycalculator.databinding.ActivityMainBinding
import java.lang.ArithmeticException

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding

    //flags to keep track of what was last input to avoid multiple decimal input
    var lastDecimal = false
    var lastNumeric = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    fun onDigit(view: View) {
        binding.numberDisplayTxt.append((view as Button).text)
        lastNumeric = true

    }

    fun onDecimal(view: View) {
        if (lastNumeric && !lastDecimal) {
            binding.numberDisplayTxt.append(".")
            lastDecimal = true
        }
    }

    fun onClear(view: View) {
        binding.numberDisplayTxt.text = ""
        lastDecimal = false
        lastNumeric = false
    }

    fun onMultiply(view: View) {
        onOperator(view)
    }

    fun onDivide(view: View) {
        onOperator(view)
    }

    fun onAdd(view: View) {
        onOperator(view)
    }

    fun onMinus(view: View) {
        onOperator(view)
    }

    fun onOperator(view: View) {
        //Toast.makeText(this,"operator button pressed",Toast.LENGTH_SHORT).show()
        if (lastNumeric && !isOperator(binding.numberDisplayTxt.text.toString())) {
            binding.numberDisplayTxt.append((view as Button).text)
            lastNumeric = false
            lastDecimal = false
        }
    }

    private fun isOperator(value: String): Boolean {
        return if (value.startsWith("-")) {
            false
        } else {
            value.contains("/") || value.contains("*") ||
                    value.contains("+") || value.contains("-")
        }
    }

    private fun removeZeroAfterDot(inputVal: String): String {
        var value = inputVal
        if (inputVal.contains(".0")) {
            value = inputVal.substring(0, inputVal.length - 2)
            return value
        }
        return value
    }

    fun onEquals(view: View) {
        if (lastNumeric) {
            var prefix = ""
            var inputValue = binding.numberDisplayTxt.text.toString()

            try {
                if (inputValue.startsWith("-")) {
                    prefix = "-"
                    //snip off the minus at the end and start with the number following
                    inputValue = inputValue.substring(1)
                }
                if (inputValue.contains("-")) {
                    //get string in display text
                    val stringValue = inputValue.toString()
                    //split the string and store in an array
                    val splitStringValue = stringValue.split("-")
                    //access the values in the array and store in reference
                    var valueOne = splitStringValue[0]
                    var valueTwo = splitStringValue[1]

                    //add back the minus for sake of the calculation
                    //after we have split the string
                    if (!prefix.isEmpty()) {
                        valueOne = prefix + valueOne
                    }
                    //create a double val to store the result of the operation
                    val opResult = ((valueOne).toDouble() - (valueTwo).toDouble())
                    //display the result on to the screen
                    binding.numberDisplayTxt.text = removeZeroAfterDot(opResult.toString())
                } else if (inputValue.contains("+")) {
                    //get string in display text
                    val stringValue = inputValue.toString()
                    //split the string and store in an array
                    val splitStringValue = stringValue.split("+")
                    //access the values in the array and store in reference
                    var valueOne = splitStringValue[0]
                    var valueTwo = splitStringValue[1]

                    //add back the minus for sake of the calculation
                    //after we have split the string
                    if (!prefix.isEmpty()) {
                        valueOne = prefix + valueOne
                    }
                    //create a double val to store the result of the operation
                    val opResult = ((valueOne).toDouble() + (valueTwo).toDouble())
                    //display the result on to the screen
                    binding.numberDisplayTxt.text = removeZeroAfterDot(opResult.toString())

                } else if (inputValue.contains("/")) {
                    //get string in display text
                    val stringValue = inputValue.toString()
                    //split the string and store in an array
                    val splitStringValue = stringValue.split("/")
                    //access the values in the array and store in reference
                    var valueOne = splitStringValue[0]
                    var valueTwo = splitStringValue[1]

                    //add back the minus for sake of the calculation
                    //after we have split the string
                    if (!prefix.isEmpty()) {
                        valueOne = prefix + valueOne
                    }
                    //create a double val to store the result of the operation
                    val opResult = ((valueOne).toDouble() / (valueTwo).toDouble())
                    //display the result on to the screen
                    binding.numberDisplayTxt.text = removeZeroAfterDot(opResult.toString())
                } else if (inputValue.contains("*")) {
                    //get string in display text
                    val stringValue = inputValue.toString()
                    //split the string and store in an array
                    val splitStringValue = stringValue.split("*")
                    //access the values in the array and store in reference
                    var valueOne = splitStringValue[0]
                    var valueTwo = splitStringValue[1]

                    //add back the minus for sake of the calculation
                    //after we have split the string
                    if (!prefix.isEmpty()) {
                        valueOne = prefix + valueOne
                    }
                    //create a double val to store the result of the operation
                    val opResult = ((valueOne).toDouble() * (valueTwo).toDouble())
                    //display the result on to the screen
                    binding.numberDisplayTxt.text = removeZeroAfterDot(opResult.toString())
                }

            } catch (e: ArithmeticException) {
                e.printStackTrace()
            }
        }
    }
}