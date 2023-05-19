package com.example.studentski_raspored.ui

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.studentski_raspored.databinding.ActivityStartBinding
import com.example.studentski_raspored.helpers.Firebase

class StartActivity : AppCompatActivity() {
    private lateinit var startBinding: ActivityStartBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        startBinding = ActivityStartBinding.inflate(layoutInflater)
        setContentView(startBinding.root)

        startBinding.btnAuthenticate.setOnClickListener() {
            val nickname = startBinding.etNickname.text.toString().trim()
            val password = startBinding.etPassword.text.toString().trim()

            if (nickname.length < 6 || nickname.length > 16 || password.length < 6 || password.length > 16) {
                Toast.makeText(
                    this,
                    "Username and password need to be 6-16 characters long",
                    Toast.LENGTH_LONG
                ).show()
            } else {
                Firebase.loginUser(nickname, password, this)
            }
        }
    }
}