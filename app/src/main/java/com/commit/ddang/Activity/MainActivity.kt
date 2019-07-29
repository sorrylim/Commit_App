package com.commit.ddang.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.commit.ddang.Fragment.AccountFragment
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
import org.naver.Naver

class MainActivity : AppCompatActivity() {

    val naver = Naver(clientId = "zjmsxbzZatZyy90LhgRy", clientSecret = "qU05l2N3LR")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.main_bnv)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)

        if (savedInstanceState == null) {
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fl, fragment, fragment.javaClass.simpleName).commit()
        }

        val search_results = naver.search().place(query = "editText.text")

        button.setOnClickListener {
            textView.text = search_results.items.forEach { place -> place.title }.toString()
        }

    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.home -> {
                val fragment = HomeFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fl, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.map -> {
                val fragment = MapFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fl, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.favorite -> {
                val fragment = FavoriteFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fl, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.account -> {
                val fragment = AccountFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fl, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
