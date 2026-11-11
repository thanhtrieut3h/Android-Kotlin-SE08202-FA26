package com.example.aimentor.sqlitedb

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DB_NAME, null, DB_VERSION) {
    companion object {
        // ten CSDL
        private const val DB_NAME = "ai_mentor"
        private const val DB_VERSION = 1

        // dinh nghia cac thong cho bang "users"
        // luu tru thong tin tai khoan nguoi dung
        const val TABLE_USER = "users" // ten bang
        // tao cac cot nam trong bang
        const val COL_ID_USER = "id" // cot "id" nam trong bang "users"
        const val COL_USERNAME_USER = "username"
        const val COL_PASSWORD_USER = "password"
        const val COL_EMAIL_USER = "email"
        const val COL_PHONE_USER = "phone"
        const val COL_ROLE_USER = "role"
        const val COL_STATUS_USER = "status_user"

        // 2 cot thoi gian dung chung cho moi bang(neu can)
        const val COL_CREATED_AT = "created_at"
        const val COL_UPDATED_AT = "updated_at"

    }
    override fun onCreate(db: SQLiteDatabase?) {
        // viet cau lenh sql de tao bang "users"
        val tableUserQuery = ("CREATE TABLE $TABLE_USER ( " +
                              "$COL_ID_USER INTEGER PRIMARY KEY AUTOINCREMENT, " +
                              "$COL_USERNAME_USER VARCHAR(30) UNIQUE NOT NULL, " +
                              "$COL_PASSWORD_USER VARCHAR(250) NOT NULL, " +
                              "$COL_EMAIL_USER VARCHAR(60) UNIQUE NOT NULL, " +
                              "$COL_ROLE_USER TINYINT DEFAULT(1), " +
                              "$COL_PHONE_USER VARCHAR(20), " +
                              "$COL_STATUS_USER TINYINT DEFAULT(1), " +
                              "$COL_CREATED_AT DATETIME, " +
                              "$COL_UPDATED_AT DATETIME )" )
        db?.execSQL(tableUserQuery) // thuc thi tao bang
    }

    override fun onUpgrade(
        db: SQLiteDatabase?,
        oldVersion: Int,
        newVersion: Int
    ) {
        if (oldVersion != newVersion) {
            // chay lai datbase
            db?.execSQL("DROP TABLE IF EXISTS $TABLE_USER")
            onCreate(db)
        }
    }
}