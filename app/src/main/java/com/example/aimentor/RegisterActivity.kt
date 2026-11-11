package com.example.aimentor

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aimentor.repositories.UserRepository

class RegisterActivity : AppCompatActivity() {
    private lateinit var tvLogin : TextView
    private lateinit var btnRegister : Button
    private lateinit var edtUsername : EditText
    private lateinit var edtPassword : EditText
    private lateinit var edtEmail : EditText
    private lateinit var edtPhone : EditText
    private lateinit var userRepository: UserRepository

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)
        tvLogin     = findViewById(R.id.tvLogin)
        btnRegister = findViewById(R.id.btnSubmit)
        edtUsername = findViewById(R.id.edtUsername)
        edtPassword = findViewById(R.id.edtPassword)
        edtEmail    = findViewById(R.id.edtEmail)
        edtPhone    = findViewById(R.id.edtPhone)
        userRepository = UserRepository(this)

        btnRegister.setOnClickListener {
            val username = edtUsername.text.toString().trim()
            val password = edtPassword.text.toString().trim()
            val email    = edtEmail.text.toString().trim()
            val phone    = edtPhone.text.toString().trim()
            if (username.isEmpty()) {
                edtUsername.error = "Username is required"
                return@setOnClickListener
            }
            if (password.isEmpty()) {
                edtPassword.error = "Password is required"
                return@setOnClickListener
            }
            if (email.isEmpty()){
                edtEmail.error = "Email is required"
                return@setOnClickListener
            }
            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
                edtEmail.error = "Email invalid format"
                return@setOnClickListener
            }
            if (userRepository.checkUserExist(username)) {
                edtUsername.error = "Username existed, please choose other !"
                return@setOnClickListener
            }
            if (userRepository.checkEmailExist(email)) {
                edtEmail.error = "Email existed, please choose other !"
                return@setOnClickListener
            }
            // tien hanh them du lieu vao DB
            val insert = userRepository.addUser(username, password, email, phone)
            if (insert) {
                Toast.makeText(this, "Register successful", Toast.LENGTH_SHORT).show()
                // quay ve trang dang nhap
                val login = Intent(this, LoginActivity::class.java)
                startActivity(login)
            } else {
                Toast.makeText(this, "Register fail, please try again", Toast.LENGTH_SHORT).show()
            }
        }

        tvLogin.setOnClickListener {
            val intentLogin = Intent(this, LoginActivity::class.java)
            startActivity(intentLogin)
        }
    }
}