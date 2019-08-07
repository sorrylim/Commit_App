package com.commit.ddang.Fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.commit.ddang.Adapter.RecyclerViewAdapter
import com.commit.ddang.Item.Homefeed

import com.commit.ddang.R
import com.google.gson.GsonBuilder
import okhttp3.*
import java.io.IOException
import java.net.URL
import java.net.URLEncoder

class HomeFragment : Fragment() {

    val clientID = "O8s98Ijqq1s5wHTHVnBY"
    val clientSecret = "Oo5slCDOGd"
    lateinit var btn_search: Button
    lateinit var input_search: EditText
    lateinit var recycler_result: RecyclerView
    lateinit var mLayoutManager: RecyclerView.LayoutManager

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)

        btn_search = rootView.findViewById(R.id.home_btn_search)
        input_search = rootView.findViewById(R.id.home_input_search)
        recycler_result = rootView.findViewById(R.id.home_recycler_result)
        mLayoutManager = LinearLayoutManager(activity)
        recycler_result.layoutManager = mLayoutManager
        recycler_result.scrollToPosition(0)
        recycler_result.setHasFixedSize(true)

        btn_search.setOnClickListener {
            if (input_search.text.isEmpty()) {
                return@setOnClickListener
            }

            fetchJson(input_search.text.toString())

            val imm = activity!!.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.hideSoftInputFromWindow(input_search.windowToken, 0)

        }

        return rootView
    }

    fun fetchJson(vararg p0: String) {
        val text = URLEncoder.encode(p0[0], "UTF-8")
        println(text)
        val url = URL("https://openapi.naver.com/v1/search/local.json?query=$text&display=10&start=1&genre=")
        val formBody = FormBody.Builder()
            .add("query", text)
            .add("oisplay", "10")
            .add("start", "1")
            .add("genre", "1")
            .build()
        val request = Request.Builder()
            .url(url)
            .addHeader("X-Naver-Client-Id", clientID)
            .addHeader("X-Naver-Client-Secret", clientSecret)
            .method("GET", null)
            .build()

        val client = OkHttpClient()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                println("Failed to execute request")
            }

            override fun onResponse(call: Call, response: Response) {
                val body = response.body?.string()
                println("Success to execute request: $body")

                val gson = GsonBuilder().create()
                val homefeed = gson.fromJson(body, Homefeed::class.java)

                activity!!.runOnUiThread {
                    recycler_result.adapter = RecyclerViewAdapter(homefeed)
                    input_search.setText("")
                }
            }
        })
    }
}
