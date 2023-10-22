package com.example.cvbuilder.database

import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class DatabaseHelper(context: Context) : SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION) {

    companion object {
        private const val DATABASE_NAME = "cv_builder.db"
        private const val DATABASE_VERSION = 1

        // Define your table and column names
        const val TABLE_CV = "cv_data"
        const val COLUMN_ID = "_id"
        const val COLUMN_NAME = "name"
        const val COLUMN_ROLL_NUMBER = "roll_number"
        const val COLUMN_CGPA = "cgpa"
        const val COLUMN_DEGREE = "degree"
        const val COLUMN_GENDER = "gender"
        const val COLUMN_DATE_OF_BIRTH = "date_of_birth"
        const val COLUMN_INTEREST = "interest"

    }

    override fun onCreate(db: SQLiteDatabase) {
        val createTableSQL = "CREATE TABLE $TABLE_CV (" +
                "$COLUMN_ID INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "$COLUMN_NAME TEXT, " +
                "$COLUMN_ROLL_NUMBER TEXT, " +
                "$COLUMN_CGPA DOUBLE, " +
                "$COLUMN_DEGREE TEXT, " +
                "$COLUMN_GENDER TEXT, " +
                "$COLUMN_DATE_OF_BIRTH TEXT, " +
                "$COLUMN_INTEREST TEXT);"

        db.execSQL(createTableSQL)
    }

    override fun onUpgrade(db: SQLiteDatabase, oldVersion: Int, newVersion: Int) {
        db.execSQL("DROP TABLE IF EXISTS $TABLE_CV")
        onCreate(db)
    }
}
