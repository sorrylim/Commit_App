package com.commit.ddang.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import com.commit.ddang.R
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import kotlinx.android.synthetic.main.activity_signup.*

class SignupActivity : AppCompatActivity() {

    private lateinit var auth: FirebaseAuth
    private lateinit var email: String
    private lateinit var password: String
    private lateinit var mainIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_signup)

        auth = FirebaseAuth.getInstance()
        mainIntent = Intent(this, MainActivity::class.java)

        signup_btn_signup.setOnClickListener { signUp() }
        auth.signOut()
    }

    public override fun onStart() {
        super.onStart()
        val currentUser = auth.currentUser
        updateUI(currentUser)
    }

    private fun valiName(): Boolean {
        val textName = signup_text_input_name.editText?.text.toString().trim()

        return if (textName.isEmpty()) {
            signup_text_input_name.error = "Field can't be empty"
            false
        } else {
            signup_text_input_name.error = null
            true
        }
    }

    private fun valiEmail(): Boolean {
        val textEmail = signup_text_input_email.editText?.text.toString().trim()

        return if (textEmail.isEmpty()) {
            signup_text_input_email.error = "Field can't be empty"
            false
        } else {
            signup_text_input_email.error = null
            true
        }
    }

    private fun valiPwd(): Boolean {
        val textPwd = signup_text_input_pwd.editText?.text.toString().trim()
        val textPwdConfirm = signup_text_input_pwd_confirm.editText?.text.toString().trim()

        if (textPwd.isNotEmpty() && textPwdConfirm.isNotEmpty() && textPwd == textPwdConfirm) {
            signup_text_input_pwd.error = null
            signup_text_input_pwd_confirm.error = null
            return true
        } else {
            if (textPwd != textPwdConfirm) {
                signup_text_input_pwd.error = null
                signup_text_input_pwd_confirm.error = "Password are not matching"
            }
            if (textPwd.isEmpty()) {
                signup_text_input_pwd.error = "Filed can't be empty"
            }
            if (textPwdConfirm.isEmpty()) {
                signup_text_input_pwd_confirm.error = "Filed can't be empty"
            }
            return false
        }
    }

    private fun valiPhone(): Boolean {
        val textPhone = signup_text_input_phone_num.editText?.text.toString().trim()

        return if (textPhone.isEmpty()) {
            signup_text_input_email.error = "Field can't be empty"
            false
        } else {
            signup_text_input_email.error = null
            true
        }
    }

    private fun signUp() {
        if (!valiEmail() || !valiPwd() || !valiPhone() || !valiName()) {
            return
        } else {
            email = signup_text_input_email.editText!!.text.toString()
            password = signup_text_input_pwd.editText!!.text.toString()
            auth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this) { task ->
                    if (task.isSuccessful) {
                        val user = auth.currentUser
                        updateUI(user)
                    } else {
                        Log.w("SignupActivity", "createUserWithEmail:failure", task.exception)
                        Toast.makeText(
                            baseContext, "Authentication Failed.", Toast.LENGTH_SHORT
                        ).show()
                        updateUI(null)
                    }
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
