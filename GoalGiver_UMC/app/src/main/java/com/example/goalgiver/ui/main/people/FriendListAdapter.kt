package com.example.goalgiver.ui.main.people

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goalgiver.R
import com.example.goalgiver.ui.main.goal.AddGoalTeamList

class FriendListAdapter(val itemList: ArrayList<FriendItem>): RecyclerView.Adapter<FriendListAdapter.FriendListViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): FriendListViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_friendlist, parent, false)
        return FriendListViewHolder(view)
    }

    override fun onBindViewHolder(holder: FriendListViewHolder, position: Int) {
        val friendItem = itemList[position] // 현재 위치의 친구 아이템을 가져옵니다.

        holder.tv_name.text = itemList[position].name
        holder.tv_img.setImageResource(R.drawable.img_books)

        holder.setGoalItem(friendItem)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }
    

    inner class FriendListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tv_name = itemView.findViewById<TextView>(R.id.tv_friendlist_name)
        val tv_img = itemView.findViewById<ImageView>(R.id.iv_friendlist_profile)

        fun setGoalItem(friendItem: FriendItem) {
            val addTeamItem = AddGoalTeamList(friendItem.name, friendItem.profileImg, false)
            // addTeamItem을 필요로 하는 다른 로직을 여기서 처리할 수 있습니다.

        }
    }
}