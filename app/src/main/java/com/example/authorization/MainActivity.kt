package com.example.authorization

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val email = findViewById<EditText>(R.id.emailEditText)
        val userName = findViewById<EditText>(R.id.userNameEditText)
        val firstName = findViewById<EditText>(R.id.firstNameEditText)
        val lastName = findViewById<EditText>(R.id.lastNameEditText)
        val age = findViewById<EditText>(R.id.ageEditText)

        val saveBtn = findViewById<Button>(R.id.saveButton)
        val clearBtn = findViewById<Button>(R.id.clearButton)

        var success :Boolean

        saveBtn.setOnClickListener {
            success = true
            if (email.text.toString().isNotEmpty() &&
                userName.text.toString().isNotEmpty() &&
                firstName.text.toString().isNotEmpty() &&
                lastName.text.toString().isNotEmpty() &&
                age.text.toString().isNotEmpty() ) {

                if (userName.text.toString().length < 10) {
                    Toast.makeText(applicationContext, "Username არ შეიძლება იყოს 10 სიმბოლოზე ნაკლები.", Toast.LENGTH_SHORT).show()
                    success = false
                }
                if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email.text.toString()).matches()) {
                    Toast.makeText(applicationContext, "Email არ არის ვალიდური.", Toast.LENGTH_SHORT).show()
                    success = false
                }
                try {
                    if (age.text.toString().toInt() in 0..144) {
                        Log.v(TAG, "Correct age format")
                    } else Toast.makeText(applicationContext, "გთხოვთ მიუთითოთ სწორი ასაკი", Toast.LENGTH_SHORT).show()
                } catch (e: NumberFormatException) {
                    success = false}
            } else {
                Toast.makeText(applicationContext, "გთხოვთ შეავსოთ ყველა ველი", Toast.LENGTH_LONG).show()
                success = false
            }
            if (success)
                Toast.makeText(applicationContext, "თქვენ წარმატებით გაიარეთ აუტორიზაცია.", Toast.LENGTH_LONG).show()
        }

        clearBtn.setOnLongClickListener {
            email.setText("")
            userName.setText("")
            firstName.setText("")
            lastName.setText("")
            age.setText("")
            true
        }
    }
}