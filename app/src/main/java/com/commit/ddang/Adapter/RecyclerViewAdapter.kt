package com.commit.ddang.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.commit.ddang.Item.Homefeed
import com.commit.ddang.Item.Item
import com.commit.ddang.R
import kotlinx.android.synthetic.main.item_home.view.*

class RecyclerViewAdapter(val homefeed: Homefeed) : RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder>() {
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(homefeed.items[position])
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_home, parent, false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int {
        return homefeed.items.count()
    }

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(data: Item) {
            itemView.item_home_text_title.text = data.title.replace("<b>", "").replace("</b>", "")
            itemView.item_home_text_telephone.text = "전화: ${data.telephone}"
            itemView.item_home_text_address.text = "주소: ${data.address}"
        }
    }
}
