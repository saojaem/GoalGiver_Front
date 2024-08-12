package com.example.goalgiver.ui.main.people

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goalgiver.R

class FriendListAdapter(val itemList: ArrayList<FriendItem>): RecyclerView.Adapter<FriendListAdapter.FriendListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_friendlist, parent, false)
        return FriendListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendListViewHolder, position: Int) {
        holder.tv_name.text = itemList[position].name
        holder.tv_img.setImageResource(R.drawable.img_books)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    inner class FriendListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tv_name = itemView.findViewById<TextView>(R.id.tv_friendlist_name)
        val tv_img = itemView.findViewById<ImageView>(R.id.iv_friendlist_profile)
    }
}