package com.commit.ddang.Adapter

import android.content.Context
import android.content.Intent
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.commit.ddang.Activity.InformActivity
import com.commit.ddang.Fragment.HomeFragment
import com.commit.ddang.Item.Homefeed
import com.commit.ddang.Item.Item
import com.commit.ddang.R
import kotlinx.android.synthetic.main.item_view.view.*

class RecyclerViewAdapter(val context:Context, val homefeed: Homefeed) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return homefeed.items.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(itemView)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(homefeed.items.get(position))
        holder.view.setOnClickListener{
            val intent = Intent(context, InformActivity::class.java)
            context.startActivity(intent)
        }
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        fun bindItems(data: Item) {
            itemView.textView_title.text = data.title
        }
    }
}
