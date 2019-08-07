package com.commit.ddang.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.commit.ddang.Item.Homefeed
import com.commit.ddang.Item.Item
import com.commit.ddang.R

import kotlinx.android.synthetic.main.item_view.view.*

class RecyclerViewAdapter(val homefeed: Homefeed) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {

    override fun getItemCount(): Int {
        return homefeed.items.count()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerViewAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_view, parent, false)
        return ViewHolder(v)
    }

    //this method is binding the data on the list
    override fun onBindViewHolder(holder: RecyclerViewAdapter.ViewHolder, position: Int) {
        holder.bindItems(homefeed.items.get(position))
    }

    class ViewHolder(val view: View) : RecyclerView.ViewHolder(view){
        fun bindItems(data: Item) {
            itemView.textView_title.text = data.title
            itemView.textView_address.text = data.address
        }
    }
}