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

        val text:String = URLEncoder.encode(editText.text.toString(), "UTF-8")
        val apiURL = "https://openapi.naver.com/v1/search/local.json"+ text

        val url = URL(apiURL)
        val con:HttpURLConnection = url.openConnection() as HttpURLConnection

        con.setRequestMethod("GET")
        con.setRequestProperty("X-Naver-Client-Id", clientId)
        con.setRequestProperty("X-Naver-Client-Secret", clientSecret)

        val responseCode:Int = con.getResponseCode()
        var br:BufferedReader

        if(responseCode == 200)
        {
            br = BufferedReader(InputStreamReader(con.getInputStream()))
        }
        else
        {
            br = BufferedReader(InputStreamReader(con.getErrorStream()))
        }

        var inputLine:String? = br.readLine()
        var response:StringBuffer = StringBuffer()

        while(inputLine != null) {
            response.append(inputLine)
        }
        br.close()



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
            R.id.account -> {
                val fragment = AccountFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_frame, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}
