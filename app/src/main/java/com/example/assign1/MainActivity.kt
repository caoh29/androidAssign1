package com.example.assign1

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assign1.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var mainBinding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        mainBinding = ActivityMainBinding.inflate(layoutInflater)

        setContentView(mainBinding.root)

        // Check sign up button
        mainBinding.signupButton.setOnClickListener {
            // Create an Order/intent
            val openSignUpPageIntent = Intent(this, SignUpActivity::class.java)
            // Launch Activity
            startActivity(openSignUpPageIntent)
        }

        // Check Log In button
        mainBinding.loginButton.setOnClickListener {
            // Create an Order/intent
            val openLogInPageIntent = Intent(this, LogInActivity::class.java)
            // Launch Activity
            startActivity(openLogInPageIntent)
        }

        // Check Updates Button
        mainBinding.updateButton.setOnClickListener {
            // Order to open a website using a browser (implicit)
            val githubChangelogUrl = "https://github.blog/changelog/"
            val openGithubChangelogUrl = Intent(Intent.ACTION_VIEW, Uri.parse(githubChangelogUrl))
            // Launch Activity
            startActivity(openGithubChangelogUrl)
        }

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }
}