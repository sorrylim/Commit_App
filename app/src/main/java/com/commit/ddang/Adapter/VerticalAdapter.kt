package com.commit.ddang.Adapter

import android.app.Activity
import android.app.Application
import android.app.PendingIntent.getActivity
import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.commit.ddang.Activity.InformActivity
import com.commit.ddang.Fragment.HomeFragment
import com.commit.ddang.Item.Homefeed
import com.commit.ddang.Item.Item
import com.commit.ddang.Item.Recommend
import com.commit.ddang.R
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.item_view.view.*
import kotlinx.android.synthetic.main.parent_rowlayout.view.*
import okhttp3.*
import okhttp3.internal.Internal.instance
import java.io.IOException
import java.net.URL
import java.net.URLEncoder

class VerticalAdapter(val AItext: ArrayList<Recommend>, activity : Activity) : RecyclerView.Adapter<VerticalAdapter.ViewHolder>() {
    val clientId:String = "zjmsxbzZatZyy90LhgRy"
    val clientSecret:String = "tUYfairJPI"

    val mactivity:Activity = activity

    override fun getItemCount(): Int {
        return AItext.size
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): VerticalAdapter.ViewHolder {
        val itemView : View = LayoutInflater.from(parent.context).inflate(R.layout.parent_rowlayout, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: VerticalAdapter.ViewHolder, position: Int) {
        fun fetchJson(vararg p0: String) {
            val text:String = URLEncoder.encode("성당동 식당", "UTF-8")
            val apiURL = "https://openapi.naver.com/v1/search/local.json?query=$text&display=10&start=1"

            val url = URL(apiURL)

            val request = Request.Builder()
                .url(url)
                .addHeader("X-Naver-Client-Id", clientId)
                .addHeader("X-Naver-Client-Secret", clientSecret)
                .method("GET", null)
                .build()

            val client = OkHttpClient()
            client.newCall(request).enqueue(object: Callback {
                override fun onResponse(call: Call?, response: Response?) {

                    mactivity.runOnUiThread {
                        val body = response?.body()?.string()
                        println("Success to execute request : $body")

                        val gson = GsonBuilder().create()

                        val homefeed = gson.fromJson(body, Homefeed::class.java)

                        holder.childrecyclerView.setHasFixedSize(true)
                        holder.childrecyclerView.layoutManager = LinearLayoutManager(mactivity, LinearLayout.HORIZONTAL, false)
                        holder.childrecyclerView.adapter = RecyclerViewAdapter(homefeed)
                    }
                }

                override fun onFailure(call: Call?, e: IOException?) {
                    println("Failed to execute request")
                }
            })
        }
        holder.bindItems(AItext.get(position))
        fetchJson(" ")
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val childrecyclerView : RecyclerView = view.findViewById(R.id.ChildRV)
        fun bindItems(data: Recommend) {
            itemView.itemname.text = data.recommend
        }
    }

}
