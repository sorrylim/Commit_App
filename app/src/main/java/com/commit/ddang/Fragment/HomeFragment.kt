package com.commit.ddang.Fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
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
import android.widget.Button
import android.widget.EditText
import androidx.recyclerview.widget.RecyclerView
import com.commit.ddang.Adapter.VerticalAdapter
import com.commit.ddang.Item.Homefeed
import com.commit.ddang.Item.Recommend
import okhttp3.*
import com.google.gson.GsonBuilder
import java.io.IOException

class HomeFragment() : Fragment() {

    lateinit var childrecyclerView : RecyclerView
    lateinit var parentrecyclerView : RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val rootView = inflater.inflate(R.layout.fragment_home, container, false)
        val rootView1 = inflater.inflate(R.layout.parent_rowlayout, container, false)
        childrecyclerView = rootView1.findViewById(R.id.ChildRV)
        parentrecyclerView = rootView.findViewById(R.id.ParentRV)

        val AItext = arrayListOf<Recommend>(
            Recommend("뭐하는날1"), Recommend("뭐하는날2"), Recommend("뭐하는날3")
        )

        parentrecyclerView.setHasFixedSize(true)
        parentrecyclerView.layoutManager = LinearLayoutManager(activity)
        parentrecyclerView.adapter = VerticalAdapter(AItext, activity!!)

        return rootView
    }


}
