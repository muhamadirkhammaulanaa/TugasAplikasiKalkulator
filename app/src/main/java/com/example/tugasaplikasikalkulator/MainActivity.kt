package com.example.tugasaplikasikalkulator


import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class MainActivity : AppCompatActivity() {

    private lateinit var tvDisplay: TextView
    private var currentNumber: String = ""
    private var operator: String = ""
    private var firstNumber: Double = 0.0
    private var isOperatorClicked: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvDisplay = findViewById(R.id.tvDisplay)

        val buttons = listOf<Button>(
            findViewById(R.id.btn0), findViewById(R.id.btn1), findViewById(R.id.btn2),
            findViewById(R.id.btn3), findViewById(R.id.btn4), findViewById(R.id.btn5),
            findViewById(R.id.btn6), findViewById(R.id.btn7), findViewById(R.id.btn8),
            findViewById(R.id.btn9)
        )

        for (button in buttons) {
            button.setOnClickListener { appendNumber((it as Button).text.toString()) }
        }

        findViewById<Button>(R.id.btnAdd).setOnClickListener { setOperator("+") }
        findViewById<Button>(R.id.btnSubtract).setOnClickListener { setOperator("-") }
        findViewById<Button>(R.id.btnMultiply).setOnClickListener { setOperator("*") }
        findViewById<Button>(R.id.btnDivide).setOnClickListener { setOperator("/") }

        findViewById<Button>(R.id.btnClear).setOnClickListener { clearDisplay() }

        findViewById<Button>(R.id.btnEquals).setOnClickListener { calculateResult() }

        findViewById<Button>(R.id.btnDecimal).setOnClickListener { appendDecimal() }
    }

    private fun appendNumber(number: String) {
        if (isOperatorClicked) {
            currentNumber = ""
            isOperatorClicked = false
        }
        currentNumber += number
        tvDisplay.text = currentNumber
    }

    private fun setOperator(op: String) {
        if (currentNumber.isNotEmpty()) {
            firstNumber = currentNumber.toDouble()
            operator = op
            isOperatorClicked = true
        }
    }

    private fun calculateResult() {
        if (currentNumber.isNotEmpty()) {
            val secondNumber = currentNumber.toDouble()
            val result = when (operator) {
                "+" -> firstNumber + secondNumber
                "-" -> firstNumber - secondNumber
                "*" -> firstNumber * secondNumber
                "/" -> firstNumber / secondNumber
                else -> 0.0
            }
            tvDisplay.text = result.toString()
            currentNumber = result.toString()
            firstNumber = 0.0
            operator = ""
        }
    }

    private fun clearDisplay() {
        currentNumber = ""
        firstNumber = 0.0
        operator = ""
        tvDisplay.text = "0"
    }

    private fun appendDecimal() {
        if (!currentNumber.contains(".")) {
            currentNumber += "."
            tvDisplay.text = currentNumber
        }
    }
}