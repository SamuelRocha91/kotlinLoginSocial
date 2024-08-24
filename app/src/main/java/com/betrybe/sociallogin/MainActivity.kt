package com.betrybe.sociallogin

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class MainActivity : AppCompatActivity() {

    private val campoEmail: TextInputLayout by lazy { findViewById(R.id.email_text_input_layout) }
    private val campoSenha: TextInputLayout by lazy { findViewById(R.id.password_text_input_layout) }
    private val botaoEntrar: Button by lazy { findViewById(R.id.login_button) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        campoSenha.editText?.setOnKeyListener { _, _, _ ->
            val inputText = campoEmail.editText?.text.toString()
            val senha = campoSenha.editText?.text.toString()
            println(inputText)
            println(senha)
            if (inputText.isNotEmpty() && senha.isNotEmpty()) {
                botaoEntrar.isEnabled = true
            } else {
                botaoEntrar.isEnabled = false
            }
            false
        }
        campoEmail.editText?.setOnKeyListener { _, _, _ ->

            val inputText = campoEmail.editText?.text.toString()
            val senha = campoSenha.editText?.text.toString()
            if (inputText.isNotEmpty() && senha.isNotEmpty()) {
                botaoEntrar.isEnabled = true
            } else {
                botaoEntrar.isEnabled = false
            }
            false
        }

        botaoEntrar.setOnClickListener{
            val email = validarEmail() && campoEmail.editText?.text.toString().isNotEmpty()
            val senha = validarSenha() && campoSenha.editText?.text.toString().isNotEmpty()

            if ( email && senha ) {
                Snackbar.make(botaoEntrar , R.string.text_label, Snackbar.LENGTH_LONG).show()
            }
        }
    }

    private fun validarEmail(): Boolean {
        val inputText = campoEmail.editText?.text.toString()
        val regex = Regex("^[A-Za-z0-9.]+@[A-Za-z]+\\.[A-Za-z]+$")
        if (!regex.matches(inputText)) {
            campoEmail.error = "Email inválido"
            return false
        } else {
            campoEmail.error = ""
            return true
        }
    }

    private fun validarSenha(): Boolean {
        val senha = campoSenha.editText?.text.toString()
        if (senha.length <= 4) {
            campoSenha.error = "Senha deve ter mais de 4 caracteres"
            return false
        } else {
            campoSenha.error = ""
            return true;
        }
    }
}
