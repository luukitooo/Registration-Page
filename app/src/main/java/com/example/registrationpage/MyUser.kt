package com.example.registrationpage

data class MyUser(
    val email: String,
    val password: String,
    val username: String,
    val age: Short,
) {
    override fun toString(): String {
        return "Username: $username\n" +
                "Email: $email\n" +
                "Age: $age"
    }
}
