package com.commit.ddang.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.commit.ddang.Fragment.FavoriteFragment
import com.commit.ddang.Fragment.HomeFragment
import com.commit.ddang.Fragment.MapFragment
import com.commit.ddang.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.activity_main.*

import java.net.URL
import java.net.URLEncoder
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

class MainActivity : AppCompatActivity() {

    val clientId:String = "zjmsxbzZatZyy90LhgRy"
    val clientSecret:String = "zqU05l2N3LR"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.main_bnv)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)

        if (savedInstanceState == null) {
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_frame, fragment, fragment.javaClass.simpleName).commit()

        }
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.home -> {
                val fragment = HomeFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.map -> {
                val fragment = MapFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.favorite -> {
                val fragment = FavoriteFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
