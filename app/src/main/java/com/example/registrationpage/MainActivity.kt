package com.example.registrationpage

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.children
import com.example.registrationpage.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var validator: MainActivityValidator

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        validator = MainActivityValidator(this, binding)
        setContentView(binding.root)

        onClickListeners()

    }

    private fun onClickListeners() {
        binding.saveButton.setOnClickListener {
            register()
        }

        binding.clearButton.setOnLongClickListener {
            clearTheLines()
            true
        }
    }

    private fun clearTheLines() {
        binding.root.children.forEach { if (it is AppCompatEditText) it.text?.clear() }
    }

    private fun register() {
        val email = binding.emailEditText.text.toString()
        val password = binding.passwordEditText.text.toString()
        val repeatedPassword = binding.repeatPasswordEditText.text.toString()
        val firstName = binding.firstNameEditText.text.toString()
        val lastName = binding.lastNameEditText.text.toString()
        val age = try {
            binding.ageEditText.text.toString().toShort()
        } catch (e: NumberFormatException) { 0 }

        if (validator.isEverythingValid(email, password, repeatedPassword, firstName + lastName, age))
            Toast.makeText(
                this,
                MyUser(email, password, "$firstName $lastName", age).toString(),
                Toast.LENGTH_SHORT
            ).show()
    }
}