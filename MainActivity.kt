package com.example.cvbuilder

import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.example.cvbuilder.databinding.ActivityMainBinding
import com.example.cvbuilder.database.CVDataSource // Import CVDataSource
import com.example.cvbuilder.database.CVData // Import CVData

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val degreeOptions = arrayOf("Information Technology", "Software Engineering", "Computer Science")
        val adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, degreeOptions)
        binding.spinnerDegree.adapter = adapter

        binding.btnPreviewCV.setOnClickListener { previewCV() }
    }

    private fun previewCV() {
        // Extract data using binding
        val rollNumber = binding.editRollNumber.text.toString()
        val name = binding.editName.text.toString()
        val cgpa = binding.editCGPA.text.toString().toDoubleOrNull()
        val degree = binding.spinnerDegree.selectedItem.toString()
        val gender = when (binding.radioGroupGender.checkedRadioButtonId) {
            binding.radioMale.id -> "Male"
            binding.radioFemale.id -> "Female"
            else -> "N/A"
        }
        val day = binding.editDateOfBirth.dayOfMonth
        val month = binding.editDateOfBirth.month + 1
        val year = binding.editDateOfBirth.year
        val dateOfBirth = "$day/$month/$year"
        val academiaInterest = binding.checkAcademia.isChecked
        val industryInterest = binding.checkIndustry.isChecked
        val businessInterest = binding.checkBusiness.isChecked

        var interest = ""

        if (academiaInterest) {
            interest = "Academia"
        } else if (industryInterest) {
            interest = "Industry"
        } else {
            interest = "Business"
        }

        val message = "Name: $name\n" +
                "Roll Number: $rollNumber\n" +
                "Gender: $gender\n" +
                "Degree: $degree\n" +
                "CGPA: $cgpa\n" +
                "Date of Birth: $dateOfBirth\n" +
                "Interest: $interest\n"


        // Display the message in a Toast
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()


        val cvData = CVData(
            name, rollNumber, cgpa ?: 0.0, degree, gender, dateOfBirth, interest
        )

        // Store the data in the database
        val dataSource = CVDataSource(this)
        dataSource.insertCVData(cvData)

        val data = dataSource.getAllCVData()

        // Store the data in the database

    }
}
