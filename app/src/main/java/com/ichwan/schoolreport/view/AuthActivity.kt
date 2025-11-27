package com.ichwan.schoolreport.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ichwan.schoolreport.core.AlkarimApp
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

            lifecycleScope.launch {
                try {
                    val response = (application as AlkarimApp).apiClient.instance.login(loginRequest)
                    if (!response.isSuccessful) {
                        Toast.makeText(
                            this@AuthActivity,
                            "Login failed: ${response.errorBody()?.string()}",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@launch
                    }

                    val loginResponse = response.body()
                    if (loginResponse == null) {
                        Toast.makeText(
                            this@AuthActivity,
                            "Response body is null",
                            Toast.LENGTH_SHORT
                        ).show()
                        return@launch
                    }

                    val intent = Intent(this@AuthActivity, MainActivity::class.java)
                    intent.putExtra("regnumber", loginResponse.regnumber)
                    startActivity(intent)
                    finish()

                } catch (e: Exception) {
                    Toast.makeText(
                        this@AuthActivity,
                        "Error: ${e.localizedMessage}",
                        Toast.LENGTH_LONG
                    ).show()
                    e.printStackTrace()
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