package com.example.aimentor.DemoActivity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Patterns
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import com.example.aimentor.R

class DemoIntentActivity : AppCompatActivity() {
    private lateinit var edtUrl : EditText
    private lateinit var btnUrl : Button
    private lateinit var btnSendData : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_demo_intent)
        edtUrl = findViewById<EditText>(R.id.edtUrl)
        btnUrl = findViewById<Button>(R.id.btnUrl)
        btnSendData = findViewById<Button>(R.id.btnSendData)

        btnSendData.setOnClickListener {
            var data : String = edtUrl.text.toString().trim()
            // gui du lieu nay sang TestSecondActivity
            if (data.isBlank()){
                edtUrl.error = "Data is required"
            } else {
                val intent = Intent(this, TestSecondActivity::class.java).apply {
                    putExtra("MY_DATA_TEST", data)
                    putExtra("MY_AGE", 20)
                    putExtra("MY_ADDRESS", "HA NOI")
                    putExtra("IS_ADMIN", true)
                }
                startActivity(intent)
            }
        }

        btnUrl.setOnClickListener {
            val url : String = edtUrl.text.toString().trim() // url ma nguoi dung nhap
            if (!isValidUrl(url)){
                edtUrl.error = "URL invalid format"
            } else {
                openWebPage(url, this)
            }
        }
    }
    private fun isValidUrl(url: String) : Boolean {
        return Patterns.WEB_URL.matcher(url).matches()
    }
    private fun openWebPage(url : String, context : Context){
        val webPage : Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webPage)
        // kiem tra xem ung dung co ho tro mo website len ko ?
        if (intent.resolveActivity(context.packageManager) != null){
            context.startActivity(intent) // mo website len
        }
    }
}