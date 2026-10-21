package com.example.aimentor.DemoEvent

import android.os.Bundle
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aimentor.R

class TestEventActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_event)
        // tim phan tu ngoai view
        val edtData = findViewById<EditText>(R.id.edtData)
        val cbAgree = findViewById<CheckBox>(R.id.cbAgree)
        val btnClickMe = findViewById<Button>(R.id.btnClickMe)
        val radGender = findViewById<RadioGroup>(R.id.radGroupGender)

        // block(khoa) cac phan tu lai
        edtData.isEnabled = false
        btnClickMe.isEnabled = false
        cbAgree.setOnCheckedChangeListener{ _, isChecked ->
            if (isChecked) {
                edtData.isEnabled = true
                btnClickMe.isEnabled = true
            } else {
                edtData.isEnabled = false
                btnClickMe.isEnabled = false
            }
        }
        btnClickMe.setOnClickListener{
            val inputString = edtData.text.toString().trim()
            if (inputString.isBlank()){
                edtData.setError("Data is requried")
                Toast.makeText(this, "Data is requried", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, inputString, Toast.LENGTH_SHORT).show()
            }
        }
        radGender.setOnCheckedChangeListener{ _, checkedId ->
            when(checkedId){
                R.id.radMale -> {
                    Toast.makeText(this, "Your gender is Male", Toast.LENGTH_SHORT).show()
                }
                R.id.radFemale -> {
                    Toast.makeText(this, "Your gender is Female", Toast.LENGTH_SHORT).show()
                }
                R.id.radOther -> {
                    Toast.makeText(this, "Your gender is Other", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}