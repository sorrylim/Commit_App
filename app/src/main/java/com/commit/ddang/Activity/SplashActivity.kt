package com.commit.ddang.Activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.commit.ddang.R

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)

        val loginIntent = Intent(this, LoginActivity::class.java)
        val mainIntent = Intent(this, MainActivity::class.java)

        startActivity(loginIntent)
        finish()

        //TODO 로그인 여부에 따라 MainActivity로 이동하는 코드 작성
    }
}
