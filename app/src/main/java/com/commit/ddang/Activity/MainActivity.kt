package com.commit.ddang.Activity

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.core.view.ViewCompat
import com.commit.ddang.Fragment.AccountFragment
import com.commit.ddang.Fragment.FavoriteFragment
import com.commit.ddang.Fragment.HomeFragment
import com.commit.ddang.Fragment.MapFragment
import com.commit.ddang.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.snackbar.BaseTransientBottomBar
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    var time: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.main_bottom_navigation_view)
        bottomNavigationView.setOnNavigationItemSelectedListener(navListener)
        if (savedInstanceState == null) {
            val fragment = HomeFragment()
            supportFragmentManager.beginTransaction()
                .replace(R.id.main_fragment_view, fragment, fragment.javaClass.simpleName).commit()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.menu_home, menu)
        val searchView = menu!!.findItem(R.id.action_search).actionView as SearchView
        searchView.queryHint = "검색어를 입력하세요."
        return true
    }

    override fun onBackPressed() {
        val snack = Snackbar.make(main_layout, "'뒤로'버튼을 한 번 더 누르면 종료됩니다.", Snackbar.LENGTH_SHORT)
            .setAnchorView(main_bottom_navigation_view).setAnimationMode(BaseTransientBottomBar.ANIMATION_MODE_FADE)
        if (System.currentTimeMillis() - time >= 1500) {
            time = System.currentTimeMillis()
            snack.config(this)
            snack.show()
        } else if (System.currentTimeMillis() - time < 1500) {
            finish()
        }
    }

    private fun Snackbar.config(context: Context) {
        val params = this.view.layoutParams as ViewGroup.MarginLayoutParams
        params.setMargins(12, 12, 12, 12)
        this.view.layoutParams = params
        this.view.background = context.getDrawable(R.drawable.bg_snackbar)

        ViewCompat.setElevation(this.view, 6f)
    }

    private val navListener = BottomNavigationView.OnNavigationItemSelectedListener {
        when (it.itemId) {
            R.id.home -> {
                val fragment = HomeFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_view, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.map -> {
                val fragment = MapFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_view, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.favorite -> {
                val fragment = FavoriteFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_view, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
            R.id.account -> {
                val fragment = AccountFragment()
                supportFragmentManager.beginTransaction()
                    .replace(R.id.main_fragment_view, fragment, fragment.javaClass.simpleName).commit()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }
}

