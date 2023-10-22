package com.example.cvbuilder.database

import android.content.ContentValues
import android.content.Context
import android.database.Cursor
import com.example.cvbuilder.database.DatabaseHelper

import com.example.cvbuilder.database.CVDataSource
import com.example.cvbuilder.database.CVData
import android.database.sqlite.SQLiteDatabase

class CVDataSource(context: Context) {
    private val dbHelper: DatabaseHelper = DatabaseHelper(context)

    // Insert CV data into the database
    fun insertCVData(cvData: CVData) {
        val db = dbHelper.writableDatabase
        try {
            val values = ContentValues()
            values.put(DatabaseHelper.COLUMN_NAME, cvData.name)
            values.put(DatabaseHelper.COLUMN_ROLL_NUMBER, cvData.rollNumber)
            values.put(DatabaseHelper.COLUMN_CGPA, cvData.cgpa)
            values.put(DatabaseHelper.COLUMN_DEGREE, cvData.degree)
            values.put(DatabaseHelper.COLUMN_GENDER, cvData.gender)
            values.put(DatabaseHelper.COLUMN_DATE_OF_BIRTH, cvData.dateOfBirth)
            values.put(DatabaseHelper.COLUMN_INTEREST, cvData.interest)


            db.insert(DatabaseHelper.TABLE_CV, null, values)
        } catch (e: Exception) {
            e.printStackTrace()
        } finally {
            db.close()
        }
    }

    // Retrieve all CV data from the database
    fun getAllCVData(): List<CVData> {
        val db = dbHelper.readableDatabase
        val cvDataList = mutableListOf<CVData>()

        val query = "SELECT * FROM ${DatabaseHelper.TABLE_CV}"
        val cursor: Cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()) {

            val name = cursor.getColumnIndex(DatabaseHelper.COLUMN_NAME)
            val rollNumber = cursor.getColumnIndex(DatabaseHelper.COLUMN_ROLL_NUMBER)
            val cgpa = cursor.getColumnIndex(DatabaseHelper.COLUMN_CGPA)
            val degree = cursor.getColumnIndex(DatabaseHelper.COLUMN_DEGREE)
            val gender = cursor.getColumnIndex(DatabaseHelper.COLUMN_GENDER)
            val dateOfBirth = cursor.getColumnIndex(DatabaseHelper.COLUMN_DATE_OF_BIRTH)
            val interest = cursor.getColumnIndex(DatabaseHelper.COLUMN_INTEREST)


            val cvData = CVData(cursor.getString(name), cursor.getString(rollNumber), cursor.getString(cgpa).toDouble(), cursor.getString(degree), cursor.getString(gender), cursor.getString(dateOfBirth), cursor.getString(interest))
            cvDataList.add(cvData)
        }

        cursor.close()
        db.close()

        return cvDataList
    }
}
