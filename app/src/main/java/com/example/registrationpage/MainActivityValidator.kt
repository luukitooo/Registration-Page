package com.example.registrationpage

import android.content.Context
import android.view.View
import android.widget.Toast
import androidx.appcompat.widget.AppCompatEditText
import androidx.core.view.children
import com.example.registrationpage.databinding.ActivityMainBinding

class MainActivityValidator(private val context: Context, private val binding: ActivityMainBinding) {

    private fun linesAreEmpty(): Boolean {
        binding.root.children.forEach {
            if (it is AppCompatEditText && it.text?.isEmpty()!!) return true
        }
        return false
    }

    private fun isEmailValid(email: String) = email.contains("@") && email.contains(".")

    private fun isUsernameValid(username: String) = username.length >= 10

    private fun isAgeValid(age: Short) = age > 0

    private fun isPasswordConfirmed(password: String, repeatedPassword: String) = password == repeatedPassword

    fun isEverythingValid(
        email: String,
        password: String,
        repeatedPassword: String,
        username: String,
        age: Short
    ): Boolean {
        return if (
            !linesAreEmpty()
            && isEmailValid(email)
            && isUsernameValid(username)
            && isAgeValid(age)
            && isPasswordConfirmed(password, repeatedPassword))
            true
        else {
            if (linesAreEmpty())
                Toast.makeText(context, "Lines are empty!", Toast.LENGTH_SHORT).show()
            else if (!isEmailValid(email))
                Toast.makeText(context, "Email is invalid!", Toast.LENGTH_SHORT).show()
            else if (!isPasswordConfirmed(password, repeatedPassword))
                Toast.makeText(context, "You repeated the password incorrectly!", Toast.LENGTH_SHORT).show()
            else if (!isUsernameValid(username))
                Toast.makeText(context, "Username is too short!", Toast.LENGTH_SHORT).show()
            else if (!isAgeValid(age))
                Toast.makeText(context, "Age is invalid!", Toast.LENGTH_SHORT).show()
            return false
        }
    }
}