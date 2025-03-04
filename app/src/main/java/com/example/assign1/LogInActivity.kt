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
import com.example.assign1.databinding.ActivityLogInBinding

class LogInActivity : AppCompatActivity() {

    private lateinit var logInBinding: ActivityLogInBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        logInBinding = ActivityLogInBinding.inflate(layoutInflater)

        setContentView(logInBinding.root)

        logInBinding.logInButton.setOnClickListener {
            val enteredUsername = logInBinding.userNameInput.text.toString()
            val enteredPassword = logInBinding.passwordInput.text.toString()

            // RETRIEVE THE SAVED VARIABLES
            val sharedPreferencesGiveAccess : SharedPreferences = getSharedPreferences("User Credentials", MODE_PRIVATE)
            // ? -> Null
            val savedUserName : String? = sharedPreferencesGiveAccess.getString("user_name", "")
            val savedPassword : String? = sharedPreferencesGiveAccess.getString("password", "")

            // Check Credentials
            if (enteredUsername == savedUserName && enteredPassword == savedPassword){

                // Order to open a website using a browser (implicit)
                val githubChangelogUrl = "https://github.com/$enteredUsername"
                val openGithubChangelogUrl = Intent(Intent.ACTION_VIEW, Uri.parse(githubChangelogUrl))
                // Launch Activity
                startActivity(openGithubChangelogUrl)

            } else {
                Toast.makeText(this, "Username or password is incorrect. Try again!", Toast.LENGTH_SHORT).show()
            }

        }

        logInBinding.backButton.setOnClickListener {
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