package com.example.aimentor.DemoActivity

import android.os.Bundle
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.aimentor.R

class TestSecondActivity : AppCompatActivity() {
    private lateinit var textData : TextView
    private lateinit var textAge : TextView
    private lateinit var textAddress: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test_second)
        textData = findViewById<TextView>(R.id.tvData)
        textAge  = findViewById<TextView>(R.id.tvAge)
        textAddress = findViewById<TextView>(R.id.tvAddress)
        // noi se nhan du lieu tu DemoIntentActivity gui sang
        // MY_DATA_TEST : key du lieu tu ben DemoIntentActivity
        val myData = intent.getStringExtra("MY_DATA_TEST")
        val myAge : Int = intent.getIntExtra("MY_AGE", 0)
        val myAdd = intent.getStringExtra("MY_ADDRESS")
        val isAdmin : Boolean = intent.getBooleanExtra("IS_ADMIN", false)
        textData.text = myData
        textAge.text = myAge.toString()
        textAddress.text = myAdd
        if (isAdmin){
            Toast.makeText(this, "You are admin", Toast.LENGTH_SHORT).show()
        }
    }
}