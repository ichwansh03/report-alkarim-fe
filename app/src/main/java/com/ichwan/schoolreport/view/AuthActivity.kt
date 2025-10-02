package com.ichwan.schoolreport.view

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.ichwan.schoolreport.databinding.ActivityLoginBinding
import com.ichwan.schoolreport.databinding.ActivityRegisterBinding

class AuthActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showRegisterScreen()
    }

    private fun showRegisterScreen() {
        val registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerBinding.saveBtn.setOnClickListener {
            // Add registration logic here
            showLoginScreen()
        }
    }

    private fun showLoginScreen() {
        val loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.loginBtn.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            intent.putExtra("regnumber", loginBinding.idNumberEt.text.toString())
            startActivity(intent)
        }

        loginBinding.registerBtn.setOnClickListener {
            showRegisterScreen()
        }
    }
}
