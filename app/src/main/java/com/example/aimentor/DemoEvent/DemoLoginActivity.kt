package com.example.aimentor.DemoEvent

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aimentor.MainActivity
import com.example.aimentor.R

class DemoLoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_linear_layout_login) // view giao dien
        val edtUser = findViewById<EditText>(R.id.edtUsername) // tim phan tu ngoai view giao dien
        val edtPassword = findViewById<EditText>(R.id.edtPassword)
        val btnSubmit = findViewById<Button>(R.id.btnSubmit)
        val btnCancel = findViewById<Button>(R.id.btnCancel)

        btnSubmit.setOnClickListener {
            // bat su kien click vao button
            // kiem tra du lieu
            if (validateLogin(edtUser, edtPassword)){
                val username = edtUser.text.toString().trim(); // lay username
                val password = edtPassword.text.toString().trim(); // lay password
                // xu ly dang nhap
                if (username.equals("admin") && password.equals("123")){
                    // dang nhap thanh cong - chuyen sang man hinh khac (ManActivity)
                    val intent = Intent(this, MainActivity::class.java)
                    startActivity(intent) // chuyen man hinh App mobile
                } else {
                    // dang nhap that bai
                    Toast.makeText(this, "Account Invalid", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
    private fun validateLogin(edtUsername: EditText, edtPassword : EditText) :Boolean {
        val user = edtUsername.text.toString().trim();
        val pass = edtPassword.text.toString().trim();
        if (user.isBlank()){
            edtUsername.error = "Username is required"
            edtUsername.requestFocus()
            return false
        }
        if (pass.isBlank()){
            edtPassword.error = "Password is required"
            edtPassword.requestFocus()
            return false
        }
        return true
    }
}