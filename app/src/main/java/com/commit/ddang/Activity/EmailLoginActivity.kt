package com.commit.ddang.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.commit.ddang.R

class EmailLoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)

        val btnLogin = findViewById<Button>(R.id.email_login_btn_login)
        val btnSignUp = findViewById<Button>(R.id.email_login_btn_signup)
        val textFindPW = findViewById<TextView>(R.id.email_find_pw)

        val mainIntent = Intent(this, MainActivity::class.java)
        val signupIntent = Intent(this, SignupActivity::class.java)
        val findPWIntent = Intent(this, FindPWActivity::class.java)

        btnLogin.setOnClickListener { startActivity(mainIntent) }
        btnSignUp.setOnClickListener { startActivity(signupIntent) }
        textFindPW.setOnClickListener { startActivity(findPWIntent) }
    }
}
