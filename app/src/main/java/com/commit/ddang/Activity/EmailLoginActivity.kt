package com.commit.ddang.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.commit.ddang.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_email_login.*

class EmailLoginActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var signUpIntent: Intent
    private lateinit var mainIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_email_login)

        auth = FirebaseAuth.getInstance()
        signUpIntent = Intent(this, SignupActivity::class.java)
        mainIntent = Intent(this, MainActivity::class.java)

        email_login_btn_signup.setOnClickListener { startActivity(signUpIntent) }
        email_login_btn_login.setOnClickListener { signIn() }
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun signIn() {
        email = email_login_text_input_email.editText!!.text.toString()
        password = email_login_text_input_pwd.editText!!.text.toString()

        auth.signInWithEmailAndPassword(email, password)
            .addOnCompleteListener(this) { task ->
                if (task.isSuccessful) {
                    Log.d("EmailLoginActivity", "signInWithEmail:success")
                    val user = auth.currentUser
                    updateUI(user)
                } else {
                    Log.w("EmailLoginActivity", "signInWithEmail:failure", task.exception)
                    Toast.makeText(baseContext, "Authentication failed.",
                        Toast.LENGTH_SHORT).show()
                    updateUI(null)
                }
            }
    }

    private fun updateUI(user: FirebaseUser?) {
        if (user != null) {
            startActivity(mainIntent)
            finish()
        } else {
            return
        }
    }
}
