package com.example.goalgiver.ui.goaldetail

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goalgiver.R

class TeamProgressAdapter(val itemList: ArrayList<TeamProgressItem>): RecyclerView.Adapter<TeamProgressAdapter.TeamProgressViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamProgressAdapter.TeamProgressViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_teamprogressham, parent, false)
        return TeamProgressViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: TeamProgressAdapter.TeamProgressViewHolder,
        position: Int
    ) {
        holder.tv_name.text = itemList[position].name
        holder.tv_point.text = itemList[position].point.toString()
        holder.tv_percent.text = itemList[position].percent.toString() + "%"
        holder.pb_percent.progress = itemList[position].percent
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    inner class TeamProgressViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tv_name = itemView.findViewById<TextView>(R.id.tv_teamprogress_name)
        val tv_point = itemView.findViewById<TextView>(R.id.tv_teamprogress_point)
        val tv_percent = itemView.findViewById<TextView>(R.id.tv_teamprogress_percent)
        val pb_percent = itemView.findViewById<ProgressBar>(R.id.pb_teamprogress)
    }
}