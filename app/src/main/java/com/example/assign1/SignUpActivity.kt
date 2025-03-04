package com.example.assign1

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.example.assign1.databinding.ActivitySignUpBinding

class SignUpActivity : AppCompatActivity() {

    private lateinit var signUpBinding: ActivitySignUpBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        signUpBinding = ActivitySignUpBinding.inflate(layoutInflater)
        setContentView(signUpBinding.root)

        signUpBinding.createAccountButton.setOnClickListener{
            val userName = signUpBinding.userNameInput.text.toString()
            val password = signUpBinding.passwordInput.text.toString()
            val confirmPassword = signUpBinding.passwordConfirmInput.text.toString()

            if (userName.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()){
                Toast.makeText(this, "Please fill all the fields!", Toast.LENGTH_SHORT).show()
            } else if (password != confirmPassword){
                Toast.makeText(this, "Passwords do not match!", Toast.LENGTH_SHORT).show()
            } else {
                // Storage ORM Class Shared Preferences
                // Create a section on my storage
                val sharedPreferencesCreateSection : SharedPreferences = getSharedPreferences("User Credentials", MODE_PRIVATE)
                // Edit this section
                val sharedPreferencesEditSection : SharedPreferences.Editor = sharedPreferencesCreateSection.edit()
                // Use the section to save data
                sharedPreferencesEditSection.putString("user_name", userName)
                sharedPreferencesEditSection.putString("password", password)
                // Save Changes
                sharedPreferencesEditSection.apply()
                Toast.makeText(this, "Account created Successfully!", Toast.LENGTH_SHORT).show()
            }
        }

        signUpBinding.backButton.setOnClickListener {
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
