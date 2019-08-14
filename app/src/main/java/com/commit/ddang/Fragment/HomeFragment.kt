package com.commit.ddang.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.commit.ddang.Adapter.RecyclerViewAdapter

import com.commit.ddang.R
import kotlinx.android.synthetic.main.fragment_home.*
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL
import java.net.URLEncoder
import android.view.inputmethod.InputMethodManager
import com.commit.ddang.Item.Homefeed
import okhttp3.*
import com.google.gson.GsonBuilder
import java.io.IOException

class HomeFragment : Fragment() {

    val clientId:String = "zjmsxbzZatZyy90LhgRy"
    val clientSecret:String = "zqU05l2N3LR"

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        button_search.setOnClickListener({
            if (edt_01.text.isEmpty()) {
                return@setOnClickListener
            }

            recyclerView.layoutManager = LinearLayoutManager(activity)
            recyclerView.setHasFixedSize(true)

            fetchJson(edt_01.text.toString())

            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(edt_01.windowToken, 0)
        })
        return rootView
    }
        fun fetchJson(vararg p0: String) {
            val text:String = URLEncoder.encode(edt_01.text.toString(), "UTF-8")
            val apiURL = "https://openapi.naver.com/v1/search/local.json"+ text

            val url = URL(apiURL)

            val formBody = FormBody.Builder().add("query", "${text}")
                .add("display", "10")
                .add("start", "1")
                .add("genre", "1")
                .build()
            val request = Request.Builder()
                .url(url)
                .addHeader("X-Naver-Client-Id", clientId)
                .addHeader("X-Naver-Client-Secret", clientSecret)
                .method("GET", null)
                .build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(object: Callback {
                override fun onResponse(call: Call?, response: Response?) {
                    val body = response?.body()?.string()
                    println("Success to execute request : $body")

                    val gson = GsonBuilder().create()

                    val homefeed = gson.fromJson(body, Homefeed::class.java)


                    activity!!.runOnUiThread {
                        recyclerView.adapter = RecyclerViewAdapter(homefeed)
                    }
                }

                override fun onFailure(call: Call?, e: IOException?) {
                    println("Failed to execute request")
                }
            })
        }



}
