package com.commit.ddang.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.commit.ddang.Fragment.FavoriteFragment
import com.commit.ddang.Fragment.HomeFragment
import com.commit.ddang.Fragment.MapFragment
import com.commit.ddang.R
import com.google.android.material.bottomnavigation.BottomNavigationView

class InformActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_inform)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.inform_bnv)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.summary -> {
                val fragment = SummaryFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.inform_frame, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.menu -> {
                val fragment = MenuFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.inform_frame, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.review -> {
                val fragment = Reviewragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.inform_frame, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }

        }
        false
    }
}
