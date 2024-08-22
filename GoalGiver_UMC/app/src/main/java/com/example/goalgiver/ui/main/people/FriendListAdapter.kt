package com.example.goalgiver.ui.main.people

import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.RecyclerView
import com.example.goalgiver.R
import org.w3c.dom.Text
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

        holder.tv_delete.setOnClickListener {
            val builder = AlertDialog.Builder(holder.itemView.context)
            builder.setMessage("친구 목록에서 삭제할까요?")
                .setPositiveButton("확인",
                    DialogInterface.OnClickListener { dialog, id ->
                        itemList.removeAt(position)
                        notifyItemRemoved(position)
                        notifyItemRangeChanged(position, itemList.size)
                    })
                .setNegativeButton("취소",
                    DialogInterface.OnClickListener { dialog, id ->
                        dialog.dismiss()
                    })

            builder.show()
        }


        holder.setGoalItem(friendItem)
    }

    override fun getItemCount(): Int {
        return itemList.count()
    }


    inner class FriendListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val tv_name = itemView.findViewById<TextView>(R.id.tv_friendlist_name)
        val tv_img = itemView.findViewById<ImageView>(R.id.iv_friendlist_profile)
        val tv_delete = itemView.findViewById<TextView>(R.id.tv_friendlist_delete)

        fun setGoalItem(friendItem: FriendItem) {
            val addTeamItem = AddGoalTeamList(friendItem.name, friendItem.profileImg, false)
            // addTeamItem을 필요로 하는 다른 로직을 여기서 처리할 수 있습니다.

        }
    }
}