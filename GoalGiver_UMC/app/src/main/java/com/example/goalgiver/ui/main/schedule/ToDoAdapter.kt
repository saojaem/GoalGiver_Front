package com.example.goalgiver.ui.main.schedule

import android.app.Activity
import android.content.Intent
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goalgiver.databinding.ItemTaskBinding
import com.example.goalgiver.ui.certification.MapCertificationActivity

class ToDoAdapter(
    private val itemClickListener: (Int) -> Unit // 클릭 리스너를 추가하여 인증값 전달
) : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    private var items: List<ToDoItem> = listOf()

    fun submitList(list: List<ToDoItem>) {
        items = list
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    inner class ToDoViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ToDoItem) {
            binding.scheduleGoalIcon.text = item.scheduleIcon
            binding.taskTitle.text = item.title
            binding.scheduleStartdate.text = item.startdate
            binding.scheduleEnddate.text = item.enddate
            binding.taskStatus.text = item.status

            if (item.status == "인증") {
                binding.taskStatus.setOnClickListener {
                    itemClickListener(item.certification) //인증값 전달
                }
            }
        }
    }
}