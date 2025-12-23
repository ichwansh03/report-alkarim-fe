package com.ichwan.schoolreport.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ichwan.schoolreport.api.RetrofitClient
import com.ichwan.schoolreport.databinding.ActivityLoginBinding
import com.ichwan.schoolreport.databinding.ActivityRegisterBinding
import com.ichwan.schoolreport.model.LoginRequest
import com.ichwan.schoolreport.model.User
import com.ichwan.schoolreport.viewmodel.UserViewModel
import kotlinx.coroutines.launch

class AuthActivity : AppCompatActivity() {

    private val viewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        showLoginScreen()
    }

    private fun showRegisterScreen() {
        val registerBinding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(registerBinding.root)

        registerBinding.saveBtn.setOnClickListener {
            val name = registerBinding.nameEditText.text.toString()
            val regNumber = registerBinding.regnumberEditText.text.toString()
            val room = registerBinding.roomEditText.text.toString()
            val roles = registerBinding.rolesSpinner.selectedItem.toString()
            val gender = registerBinding.genderSpinner.selectedItem.toString()
            val password = registerBinding.passwordEditText.text.toString()

            val user = User(name, regNumber, room, roles, gender, password)

            viewModel.registerUser(user)
            showLoginScreen()
        }
    }

    private fun showLoginScreen() {
        val loginBinding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(loginBinding.root)

        loginBinding.loginBtn.setOnClickListener {
            val regNumber = loginBinding.idNumberEt.text.toString()
            val password = loginBinding.passwordEt.text.toString()

            val loginRequest = LoginRequest(regNumber, password)

            viewModel.loginUser(loginRequest)

            viewModel.loginSuccess.observe(this) { success ->
                if (success) {
                    val intent = Intent(this@AuthActivity, MainActivity::class.java)
                    intent.putExtra("regnumber", loginRequest.regnumber)
                    startActivity(intent)
                    finish()
                } else {
                    Toast.makeText(this, "Login failed", Toast.LENGTH_SHORT).show()
                }
            }

        }

        loginBinding.registerBtn.setOnClickListener {
            showRegisterScreen()
        }
        
        loginBinding.forgotPwTv.setOnClickListener {
            supportFragmentManager.beginTransaction()
                .add(android.R.id.content, FragmentForgotPw())
                .addToBackStack(null)
                .commit()
        }
    }
}