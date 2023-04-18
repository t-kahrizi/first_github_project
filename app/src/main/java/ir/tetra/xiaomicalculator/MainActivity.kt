package ir.tetra.xiaomicalculator

import GlobalVariables.Companion.addDotPermission
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewTreeObserver
import android.widget.ArrayAdapter
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import ir.tetra.xiaomicalculator.databinding.ActivityMainBinding
import net.objecthunter.exp4j.ExpressionBuilder
import java.lang.Exception
import javax.xml.xpath.XPathExpression


const val KEY_INTENT_NAME = "data"

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        onNumberClicked()
        onOperatorClicked()


    }

    private fun onOperatorClicked() {
        binding.btnNegPos.setOnClickListener {
            if (binding.txtExpression.text.isEmpty()) {
                appendText("-")
            } else if (isOperand(binding.txtExpression.text.last())) {
                appendText("-")
            }
        }

        binding.btnOnedivideX.setOnClickListener {
            val txt=binding.txtExpression.text
            if (txt.isEmpty() || isOperand(txt.last()) ){
                appendText("1/")}

        }

        binding.btnSquare.setOnClickListener {
            var txt=binding.txtExpression.text
            if (txt.isNotEmpty() && !isOperand((txt.last())) && txt.last()!='.'){
                appendText("^2")
            }

        }

        binding.btnRoot.setOnClickListener {
            var txt=binding.txtExpression.text
            if (txt.isEmpty() || isOperand((txt.last()))){
                appendText("√(")
            }


        }

        binding.btnJam.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val lastChar = binding.txtExpression.text.last()
                if (!isOperand(lastChar)) {
                    if (lastChar != '.') {
                        appendText("+")
                    } else {
                        appendText("0+")
                    }
                }
            }

        }

        binding.btnMenha.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val lastChar = binding.txtExpression.text.last()
                if (!isOperand(lastChar)) {
                    if (lastChar != '.') {
                        appendText("-")
                    } else {
                        appendText("0-")
                    }
                }
            }

        }
        binding.btnZarb.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val lastChar = binding.txtExpression.text.last()
                if (!isOperand(lastChar)) {
                    if (lastChar != '.') {
                        appendText("*")
                    } else {
                        appendText("0*")
                    }
                }
            }

        }
        binding.btnTaghsim.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val lastChar = binding.txtExpression.text.last()
                if (!isOperand(lastChar)) {
                    if (lastChar != '.') {
                        appendText("/")
                    } else {
                        appendText("0/")
                    }
                }
            }

        }
        binding.btnParantezBaz.setOnClickListener {
            appendText("(")
        }
        binding.btnParantezBaste.setOnClickListener {
            val text = binding.txtExpression.text
            if (isOperand(text.last())) {
                Toast.makeText(this, "Expression is not true!", Toast.LENGTH_SHORT).show()

            } else {
                appendText(")")
            }
        }
        binding.btnAC.setOnClickListener {
            binding.txtExpression.text = ""
            binding.txtJavab.text = ""
        }
        binding.btnPakKardan.setOnClickListener {
            val oldText = binding.txtExpression.text.toString()
            if (oldText.isNotEmpty()) {
                if (oldText.endsWith("^2") || oldText.endsWith("√(")) {
                    binding.txtExpression.text = oldText.substring(0, oldText.length - 2)
                } else {
                    binding.txtExpression.text = oldText.substring(0, oldText.length - 1)
                }
            }
        }
        binding.btnMosavi.setOnClickListener() {
            try {
                val tempStr=  binding.txtExpression.text.toString()
                val str=tempStr.replace("√","sqrt")
                val expression = ExpressionBuilder(str).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    binding.txtJavab.text = longResult.toString()
                } else {
                    binding.txtJavab.text = result.toString()
                }

            } catch (e: Exception) {
                Toast.makeText(this, "An error occured!", Toast.LENGTH_LONG).show()

            }
        }


    }

    private fun onNumberClicked() {
        binding.btn0.setOnClickListener {
            if (binding.txtExpression.text.isNotEmpty()) {
                val txt = binding.txtExpression.text.toString()
                if (!txt.endsWith("-") && !txt.endsWith("+") && !txt.endsWith("/") && !txt.endsWith(
                        "*"
                    ) && !txt.endsWith("(") && !txt.endsWith(")") && !endWithSquare(txt))
                 {
                    appendText("0")
                }
            }
        }
        binding.btn1.setOnClickListener {
          if (!endWithSquare(binding.txtExpression.text.toString()))
              appendText("1")
        }
        binding.btn2.setOnClickListener {
            if (!endWithSquare(binding.txtExpression.text.toString()))
            appendText("2")
        }

        binding.btn3.setOnClickListener {
            if (!endWithSquare(binding.txtExpression.text.toString()))
            appendText("3")
        }

        binding.btn4.setOnClickListener {
            if (!endWithSquare(binding.txtExpression.text.toString()))
            appendText("4")
        }
        binding.btn5.setOnClickListener {
            if (!endWithSquare(binding.txtExpression.text.toString()))
            appendText("5")
        }


        binding.btn6.setOnClickListener {
            if (!endWithSquare(binding.txtExpression.text.toString()))
            appendText("6")
        }

        binding.btn7.setOnClickListener {
            if (!endWithSquare(binding.txtExpression.text.toString()))
            appendText("7")
        }

        binding.btn8.setOnClickListener {
            if (!endWithSquare(binding.txtExpression.text.toString()))
            appendText("8")
        }
        binding.btn9.setOnClickListener {
            if (!endWithSquare(binding.txtExpression.text.toString()))
            appendText("9")
        }
        binding.btnDot.setOnClickListener {
            val txt = binding.txtExpression.text
            if (!endWithSquare(txt.toString())) {

                if (txt.isEmpty()) {
                    appendText("0.")
                } else if (txt.endsWith("+") || txt.endsWith("-") || txt.endsWith("*") || txt.endsWith(
                        "/"
                    ) || txt.endsWith(
                        "("
                    ) || txt.endsWith(")")
                ) {
                    appendText("0.")
                } else if (addDotPermission(txt)) {
                    //check if we have a dot in the last operand or not?
                    appendText(".")
                }
            }
        }
    }

    private fun addDotPermission(txt: CharSequence): Boolean {
        var i = (txt.length) - 1
        while (i >= 0 && !isOperand(txt[i])) {
            if (txt[i] == '.')
                return false
            i--
        }
        return true
    }


    private fun appendText(newText: String) {
        binding.txtExpression.append(newText)

        //To automatically scroll the txtExpression
        val viewTree: ViewTreeObserver =
            binding.horizontalScrollViewTxtExpression.viewTreeObserver
        viewTree.addOnGlobalLayoutListener(object : ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                binding.horizontalScrollViewTxtExpression.viewTreeObserver.removeOnGlobalLayoutListener(
                    this
                )
                binding.horizontalScrollViewTxtExpression.scrollTo(
                    binding.txtExpression.width,
                    0
                )
            }

        })
    }

    private fun isOperand(myChar: Char): Boolean {

        if (myChar == '+' || myChar == '-' || myChar == '*' || myChar == '/' || myChar == '(' ) {
            return true
        } else {
            return false
        }
    }

    private fun endWithSquare(str:String):Boolean{
        if (str.endsWith("^2"))
            return true
        else return false
    }
}