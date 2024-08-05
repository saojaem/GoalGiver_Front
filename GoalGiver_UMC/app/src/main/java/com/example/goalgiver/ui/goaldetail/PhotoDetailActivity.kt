package com.example.goalgiver.ui.goaldetail

import android.os.Bundle
import android.widget.ImageButton
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.goalgiver.databinding.ActivityPhotodetailBinding

class PhotoDetailActivity: AppCompatActivity() {

    lateinit var binding: ActivityPhotodetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityPhotodetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val imageResource = intent.getIntExtra("imageResource", 0)
        val imageView: ImageView = binding.ivPhotodetail
        imageView.setImageResource(imageResource)

        val backButton: ImageButton = binding.btnPhotodetailBack
        backButton.setOnClickListener { finish() }
    }
}