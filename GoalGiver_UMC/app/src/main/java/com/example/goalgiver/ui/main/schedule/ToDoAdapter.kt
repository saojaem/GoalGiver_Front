package com.example.goalgiver.ui.main.schedule

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.goalgiver.databinding.ItemTaskBinding

class ToDoAdapter : RecyclerView.Adapter<ToDoAdapter.ToDoViewHolder>() {

    private var items: List<ToDoItem> = listOf()

    fun submitList(list: List<ToDoItem>) {
        items = list
        notifyDataSetChanged()
    }

    fun getItems(): List<ToDoItem> {
        return items
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ToDoViewHolder {
        val binding = ItemTaskBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ToDoViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ToDoViewHolder, position: Int) {
        holder.bind(items[position])
    }

    override fun getItemCount(): Int = items.size

    class ToDoViewHolder(private val binding: ItemTaskBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(item: ToDoItem) {
            binding.taskTitle.text = item.title
            binding.taskDays.text = item.date
            binding.taskStatus.text = item.status
        }
    }
}
