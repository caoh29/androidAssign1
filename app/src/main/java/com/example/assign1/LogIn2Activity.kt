package com.example.assign1

import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assign1.databinding.ActivityLogIn2Binding

class LogIn2Activity : AppCompatActivity() {

    private lateinit var binding: ActivityLogIn2Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivityLogIn2Binding.inflate(layoutInflater)

        setContentView(binding.root)

        binding.logInButton.setOnClickListener {
            val enteredEmail = binding.emailInput.text.toString()
            val enteredPassword = binding.passwordInput.text.toString()

            // RETRIEVE THE SAVED VARIABLES
            val sharedPreferencesGiveAccess : SharedPreferences = getSharedPreferences("User Credentials 2", MODE_PRIVATE)
            // ? -> Null
            val savedEmail : String? = sharedPreferencesGiveAccess.getString("email", "")
            val savedPassword : String? = sharedPreferencesGiveAccess.getString("password", "")

            // Check Credentials
            if (enteredEmail == savedEmail && enteredPassword == savedPassword){
                // Order to open a website using a browser (implicit)
                val githubChangelogUrl = "https://github.com/$enteredEmail"
                val openGithubChangelogUrl = Intent(Intent.ACTION_VIEW, Uri.parse(githubChangelogUrl))
                // Launch Activity
                startActivity(openGithubChangelogUrl)

            } else {
                Toast.makeText(this, "Username or password is incorrect. Try again!", Toast.LENGTH_SHORT).show()
            }

        }

        binding.backButton.setOnClickListener {
            val backLoginIntent = Intent(this, MainActivity::class.java)
            startActivity(backLoginIntent)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}