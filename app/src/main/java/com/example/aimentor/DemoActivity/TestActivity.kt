package com.example.aimentor.DemoActivity

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.aimentor.R

class TestActivity : AppCompatActivity() {

    private val TAG_LOG = "ACTIVITY_LIFECYCLE" // ghi log theo doi Activity
    private lateinit var btnActivity : Button // button click chuyen man hinh

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // ham oncreate : khoi tao activity
        // noi nay dung de load giao dien cua ung dung
        setContentView(R.layout.activity_test)
        // ghi log theo doi
        Log.d(TAG_LOG, "TestActivity da duoc khoi tao")
        //onCreate : se chay dau tien va duy nhat 1 lan trong vong doi cua Activity
        btnActivity = findViewById<Button>(R.id.btnClickActivity)
        btnActivity.setOnClickListener {
            // di chuyen sang man hinh second
            val secondActivity = Intent(this, TestSecondActivity::class.java)
            startActivity(secondActivity)
        }
    }

    override fun onStart() {
        super.onStart()
        // ham nay se duoc goi truoc khi activity xuat hien
        Log.d(TAG_LOG, "onStart da khoi chay")
    }

    override fun onResume() {
        super.onResume()
        // ham nay se duoc goi khi Activity bat dau co the tuong tac voi nguoi dung
        Log.d(TAG_LOG, "onResume da duoc khoi chay")
    }

    override fun onPause() {
        super.onPause()
        // ham nay se duoc goi khi chuan bi co mot Activity moi xuat hien (TestSecondActiviy)
        Log.d(TAG_LOG, "onPause da khoi chay")
    }

    override fun onStop() {
        super.onStop()
        // ham nay se chay khi Activity hien thoi bi an di de nhuong cho Activity moi xuat hien
        // TestActivity se an di de TestSecondActivity xuat hien
        Log.d(TAG_LOG, "onStop da khoi chay")
    }
}