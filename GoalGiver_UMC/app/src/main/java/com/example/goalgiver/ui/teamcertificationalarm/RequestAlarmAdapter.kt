package com.example.goalgiver.ui.teamcertificationalarm

import android.app.Activity
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goalgiver.R
import com.example.goalgiver.ui.teamcertificationalarm.RequestAlarmActivity.Companion.REJECT_REQUEST_CODE

class RequestAlarmAdapter(val itemList: ArrayList<CertificationAlarmItem>): RecyclerView.Adapter<RequestAlarmAdapter.AlarmViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RequestAlarmAdapter.AlarmViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_alarm_request, parent, false)
        return AlarmViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestAlarmAdapter.AlarmViewHolder, position: Int) {
        holder.tv_date.text = itemList[position].date
        holder.tv_body.text = itemList[position].body
        holder.isChecked = itemList[position].isChecked

        val intent = Intent()

        if (holder.isChecked) {
            holder.iv_oldalarm.visibility = View.VISIBLE
            holder.iv_newalarm.visibility = View.INVISIBLE
            holder.layout_button.visibility = View.GONE
        } else {
            holder.iv_oldalarm.visibility = View.INVISIBLE
            holder.iv_newalarm.visibility = View.VISIBLE
            holder.layout_button.visibility = View.VISIBLE
        }

        holder.btn_accept.setOnClickListener {
            itemList[position].body = "홍길동님의 ‘파이썬 6주차 과제’ 인증을 승인했습니다."
            itemList[position].isChecked = true

            holder.tv_body.text = itemList[position].body
            holder.iv_oldalarm.visibility = View.VISIBLE
            holder.iv_newalarm.visibility = View.INVISIBLE
            holder.layout_button.visibility = View.GONE
        }

        holder.btn_reject.setOnClickListener {
            val intent = Intent(holder.context, RejectionReasonActivity::class.java)
            intent.putExtra("position", position)
            (holder.context as Activity).startActivityForResult(intent, REJECT_REQUEST_CODE)

            //context.startActivity(intent)
        }

    }

    override fun getItemCount(): Int {
        return itemList.count()
    }

    inner class AlarmViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val context = itemView.context

        val tv_date = itemView.findViewById<TextView>(R.id.tv_request_date)
        val tv_body = itemView.findViewById<TextView>(R.id.tv_request_body)
        val iv_newalarm = itemView.findViewById<ImageView>(R.id.iv_request_new)
        val iv_oldalarm = itemView.findViewById<ImageView>(R.id.iv_request_old)
        val layout_button = itemView.findViewById<LinearLayout>(R.id.layout_request_buttonLayout)
        var isChecked = false

        val btn_reject = itemView.findViewById<Button>(R.id.btn_request_reject)
        val btn_accept = itemView.findViewById<Button>(R.id.btn_request_accept)

//        init {
//            btn_reject.setOnClickListener {
//                val intent = Intent(context, RejectionReasonActivity::class.java)
//                intent.putExtra("position", adapterPosition)
//                (context as Activity).startActivityForResult(intent, REJECT_REQUEST_CODE)
//
//                //tv_body.text = "홍길동님의 ‘파이썬 6주차 과제’ 인증을 거부했습니다."
//                //context.startActivity(intent)
//            }
//        }

    }
}