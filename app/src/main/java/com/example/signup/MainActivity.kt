package com.example.signup

import WelcomeActivity
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val userNameEditText: EditText = findViewById(R.id.userNameEditText)
        val mobileNoEditText: EditText = findViewById(R.id.mobileNoEditText)
        val emailEditText: EditText = findViewById(R.id.emailEditText)
        val cityEditText: EditText = findViewById(R.id.cityEditText)
        val passwordEditText: EditText = findViewById(R.id.passwordEditText)
        val signUpButton: Button = findViewById(R.id.signUpButton)

        signUpButton.setOnClickListener {
            val userName = userNameEditText.text.toString().trim()
            val mobileNo = mobileNoEditText.text.toString().trim()
            val email = emailEditText.text.toString().trim()
            val city = cityEditText.text.toString().trim()
            val password = passwordEditText.text.toString().trim()

            if (userName.isNotEmpty() && mobileNo.isNotEmpty() && email.isNotEmpty() && city.isNotEmpty() && password.isNotEmpty()) {
                signUp(userName, mobileNo, email, city, password)
            } else {
                Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun signUp(userName: String, mobileNo: String, email: String, city: String, password: String) {
        val request = SignUpRequest(userName, mobileNo, email, city, password)

        RetrofitClient.instance.signUp(request).enqueue(object : Callback<SignUpResponse> {
            override fun onResponse(call: Call<SignUpResponse>, response: Response<SignUpResponse>) {
                if (response.isSuccessful) {
                    val signUpResponse = response.body()
                    if (signUpResponse?.success == true) {
                       // Toast.makeText(this@MainActivity, "Sign-Up Successful", Toast.LENGTH_SHORT).show()
                        val intent = Intent(this@MainActivity, WelcomeActivity::class.java)
                        intent.putExtra("userName", userName)
                        startActivity(intent)
                        finish()
                    } else {
                        Toast.makeText(this@MainActivity, signUpResponse?.message ?: "Sign-Up Failed", Toast.LENGTH_SHORT).show()
                    }
                } else {
                    Toast.makeText(this@MainActivity, "Sign-Up Failed", Toast.LENGTH_SHORT).show()
                }
            }

            override fun onFailure(call: Call<SignUpResponse>, t: Throwable) {
                Toast.makeText(this@MainActivity, t.message, Toast.LENGTH_SHORT).show()
            }
        })
    }
}
