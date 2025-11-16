package com.example.tasknest

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.tasknest.databinding.ActivityMainBinding
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val db = FirebaseFirestore.getInstance()
    private val auth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnAddTask.setOnClickListener {
            val task = binding.etTask.text.toString()
            val userId = auth.currentUser?.uid ?: return@setOnClickListener

            val data = hashMapOf("task" to task)
            db.collection("users").document(userId).collection("tasks").add(data)
        }

        binding.btnLogout.setOnClickListener {
            auth.signOut()
            finish()
        }
    }
}
