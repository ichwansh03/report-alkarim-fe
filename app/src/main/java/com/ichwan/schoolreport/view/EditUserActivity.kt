package com.ichwan.schoolreport.view

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.ichwan.schoolreport.core.RetrofitClientWrapper
import com.ichwan.schoolreport.databinding.ActivityEditUserBinding
import com.ichwan.schoolreport.model.User
import kotlinx.coroutines.launch

class EditUserActivity : AppCompatActivity() {

    private lateinit var binding: ActivityEditUserBinding
    private var user: User? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditUserBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val regnumber = intent.getStringExtra("regnumber")

        if (regnumber == null) {
            finish()
            return
        }

        lifecycleScope.launch {
            try {
                val response = RetrofitClientWrapper.apiService.getUserByRegNumber(regnumber)
                if (response.isSuccessful) {
                    user = response.body()?.data
                    populateForm()
                } else {
                    Toast.makeText(this@EditUserActivity, "Failed to load user data: ${response.message()}", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Toast.makeText(this@EditUserActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
            }
        }

        binding.saveButton.setOnClickListener {
            saveUser()
        }
    }

    private fun populateForm() {
        user?.let {
            binding.editRegnumber.setText(it.regnumber)
            binding.editName.setText(it.name)
            binding.editClass.setText(it.clsroom)
            if (it.gender.equals("Laki-laki", ignoreCase = true)) {
                binding.maleRadioButton.isChecked = true
            } else {
                binding.femaleRadioButton.isChecked = true
            }
        }
    }

    private fun saveUser() {
        val updatedUser = user?.copy(
            regnumber = binding.editRegnumber.text.toString(),
            name = binding.editName.text.toString(),
            clsroom = binding.editClass.text.toString(),
            gender = if (binding.maleRadioButton.isChecked) "Laki-laki" else "Perempuan"
        )

        if (updatedUser != null && updatedUser.id != null) {
            lifecycleScope.launch {
                try {
                    val response =
                        RetrofitClientWrapper.apiService.updateUser(updatedUser.id, updatedUser)
                    if (response.isSuccessful) {
                        Toast.makeText(this@EditUserActivity, "User updated successfully", Toast.LENGTH_SHORT).show()
                        finish()
                    } else {
                        Toast.makeText(this@EditUserActivity, "Failed to update user", Toast.LENGTH_SHORT).show()
                    }
                } catch (e: Exception) {
                    Toast.makeText(this@EditUserActivity, "Error: ${e.message}", Toast.LENGTH_SHORT).show()
                }
            }
        } else {
            Toast.makeText(this, "User ID is missing", Toast.LENGTH_SHORT).show()
        }
    }
}
