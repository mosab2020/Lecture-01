package com.example.lecture01code00

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class MainActivity : AppCompatActivity() {

    private val db = Firebase.firestore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        // Save to Firebase
        findViewById<Button>(R.id.btn_save).setOnClickListener {
            val id = findViewById<EditText>(R.id.et_id).text.toString()
            val name = findViewById<EditText>(R.id.et_name).text.toString()
            val age = findViewById<EditText>(R.id.et_age).text.toString()

            // Create a new user with a first and last name
            val person = hashMapOf(
                "id" to id,
                "name" to name,
                "age" to age
            )

            // Add a new document with a generated ID
            db.collection("persons")
                .add(person)
                .addOnSuccessListener { documentReference ->
                    Toast.makeText(
                        applicationContext,
                        "${documentReference.id}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                .addOnFailureListener { e ->
                    Toast.makeText(
                        applicationContext,
                        "$e",
                        Toast.LENGTH_SHORT
                    ).show()

                }
        }
    }
}