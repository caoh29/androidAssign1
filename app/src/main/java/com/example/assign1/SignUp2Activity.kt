package com.example.assign1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assign1.databinding.ActivitySignUp2Binding
import com.example.assign1.databinding.ActivitySignUpBinding

class SignUp2Activity : AppCompatActivity() {

    private lateinit var binding: ActivitySignUp2Binding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        binding = ActivitySignUp2Binding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.createAccountButton.setOnClickListener{
            val email = binding.emailInput.text.toString().trim()
            val password = binding.passwordInput.text.toString()
            val confirmPassword = binding.passwordConfirmInput.text.toString()

            // Check if fields are empty
            if (email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate email format
            val emailPattern = Regex("[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}")
            if (!email.matches(emailPattern)) {
                Toast.makeText(this, "Please enter a valid email address", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Validate password length
            if (password.length < 8 || password.length > 15) {
                Toast.makeText(this, "Password must be between 8 and 15 characters", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check password complexity
            val hasUpperCase = password.contains(Regex("[A-Z]"))
            val hasSpecialChar = password.contains(Regex("[@\$!%*?&]"))

            if (!hasUpperCase || !hasSpecialChar) {
                Toast.makeText(this, "Password must include at least one capital letter and one special character", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Check if passwords match
            if (password != confirmPassword) {
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            // Continue with account creation if all validations pass

            // Storage ORM Class Shared Preferences
            // Create a section on my storage
            val sharedPreferencesCreateSection : SharedPreferences = getSharedPreferences("User Credentials 2", MODE_PRIVATE)
            // Edit this section
            val sharedPreferencesEditSection : SharedPreferences.Editor = sharedPreferencesCreateSection.edit()
            // Use the section to save data
            sharedPreferencesEditSection.putString("email", email)
            sharedPreferencesEditSection.putString("password", password)
            // Save Changes
            sharedPreferencesEditSection.apply()
            Toast.makeText(this, "Account created Successfully!", Toast.LENGTH_SHORT).show()

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
