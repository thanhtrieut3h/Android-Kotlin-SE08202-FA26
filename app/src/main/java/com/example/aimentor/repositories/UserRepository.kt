package com.example.aimentor.repositories

import android.content.ContentValues
import android.content.Context
import com.example.aimentor.sqlitedb.DatabaseHelper
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

class UserRepository(context: Context) {
    private val dbHelper = DatabaseHelper(context)

    // viet phuong thuc lay ra ngay thang hien tai
    private fun getCurrentDateTime() : String {
        val dateFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return dateFormat.format(Date()) // lay ra thoi gian cua he thong hien tai
    }

    // viet phuong thuc dang ky tai khoan moi cho nguoi dung
    fun addUser(username: String, password: String, email: String, phone: String) : Boolean {
        val db = dbHelper.writableDatabase // ghi du lieu
        val currentDate = getCurrentDateTime()
        val values = ContentValues().apply {
            put(DatabaseHelper.COL_USERNAME_USER, username)
            put(DatabaseHelper.COL_PASSWORD_USER, password)
            put(DatabaseHelper.COL_EMAIL_USER, email)
            put(DatabaseHelper.COL_PHONE_USER, phone)
            put(DatabaseHelper.COL_ROLE_USER, 1)
            put(DatabaseHelper.COL_STATUS_USER, 1)
            put(DatabaseHelper.COL_CREATED_AT, currentDate)
        }
        val insert = db.insert(DatabaseHelper.TABLE_USER, null, values)
        db.close()
        return insert != 1L
        // return true : thanh cong va nguoc lai
    }
    // viet thuc kiem tra Username da ton tai chua?
    fun checkUserExist(username: String) : Boolean {
        val db = dbHelper.readableDatabase // doc du lieu
        val cursor = db.query(
            DatabaseHelper.TABLE_USER,
            arrayOf(DatabaseHelper.COL_ID_USER),
            "${DatabaseHelper.COL_USERNAME_USER} = ?",
            arrayOf(username),
            null, null, null
        )
        val exists = cursor.count > 0 // co du lieu tra ve ko?
        cursor.close()
        db.close()
        return exists // true : da ton tai username can them moi va nguoc lai
    }
    // kiem tra email da ton tai trong DB hay chua ?
    fun checkEmailExist(email: String) : Boolean {
        val db = dbHelper.readableDatabase // doc du lieu
        val cursor = db.query(
            DatabaseHelper.TABLE_USER,
            arrayOf(DatabaseHelper.COL_ID_USER),
            "${DatabaseHelper.COL_EMAIL_USER} = ?",
            arrayOf(email),
            null, null, null
        )
        val exists = cursor.count > 0 // co du lieu tra ve ko?
        cursor.close()
        db.close()
        return exists // true : da ton tai username can them moi va nguoc lai
    }
    // viet phuong thuc kiem tra dang nhap
    // kiem tra username va password dung de dang nhap co ton tai trong DB ko?

}