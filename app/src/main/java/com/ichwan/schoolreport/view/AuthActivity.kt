package com.ichwan.schoolreport.view

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.ichwan.schoolreport.databinding.ActivityLoginBinding
import com.ichwan.schoolreport.databinding.ActivityRegisterBinding
import com.ichwan.schoolreport.model.LoginRequest
import com.ichwan.schoolreport.model.User
import com.ichwan.schoolreport.util.UserRole
import com.ichwan.schoolreport.viewmodel.AuthViewModel

class AuthActivity : AppCompatActivity() {

    private val authViewModel: AuthViewModel by viewModels()


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
            val clsroom = registerBinding.roomEditText.text.toString()
            val rolesStr = registerBinding.rolesSpinner.selectedItem.toString()
            val gender = registerBinding.genderSpinner.selectedItem.toString()
            val password = registerBinding.passwordEditText.text.toString()

            val role = try {
                UserRole.valueOf(rolesStr.uppercase())
            } catch (e: Exception) {
                UserRole.STUDENT
            }

            val user = User(
                name = name,
                regnumber = regNumber,
                clsroom = clsroom,
                roles = role,
                gender = gender,
                password = password
            )

            authViewModel.registerUser(user)
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

            authViewModel.loginUser(loginRequest)

            authViewModel.loginSuccess.observe(this) { success ->
                if (success) {
                    authViewModel.userRole.observe(this) { role ->
                        if (role != null) {
                            val intent = Intent(this@AuthActivity, MainActivity::class.java)
                            intent.putExtra("regnumber", loginRequest.regnumber)
                            intent.putExtra("role", role)
                            startActivity(intent)
                            finish()
                        }
                    }
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
