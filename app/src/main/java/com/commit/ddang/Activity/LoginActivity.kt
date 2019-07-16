package com.commit.ddang.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.commit.ddang.R

class LoginActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val btnEmailLogin = findViewById<Button>(R.id.login_btn_email_login)
        val emailLoginIntent = Intent(this, EmailLoginActivity::class.java)

        btnEmailLogin.setOnClickListener { startActivity(emailLoginIntent) }
    }
}
