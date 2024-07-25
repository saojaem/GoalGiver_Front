package com.example.goalgiver.ui.goaldetail

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import com.example.goalgiver.R

class PhotoImageAdapter(private val context: Context, private val images: List<Int>): BaseAdapter() {

    override fun getCount(): Int {
        return images.size
    }

    override fun getItem(position: Int): Any {
        return images[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = convertView ?: LayoutInflater.from(context).inflate(R.layout.photocertificated_grid_item, parent, false)
        val imageView: ImageView = view.findViewById(R.id.image)
        imageView.setImageResource(images[position])
        return view
    }
}