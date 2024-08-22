package com.example.goalgiver.ui.goaldetail

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.goalgiver.R
import com.example.goalgiver.databinding.FragmentGoaldetailPhotocertificationBinding

class PhotoCertificationFragment: Fragment() {

    lateinit var binding: FragmentGoaldetailPhotocertificationBinding

    private val imageResources = listOf(
        R.drawable.img_emptystate,
        R.drawable.img_books,
        R.drawable.img_emptystate,
        R.drawable.img_books,
        R.drawable.img_emptystate,
        R.drawable.img_books,
        R.drawable.img_emptystate,
        R.drawable.img_books,
        R.drawable.img_emptystate,
        R.drawable.img_books,
        R.drawable.img_emptystate,
        R.drawable.img_books
    )
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGoaldetailPhotocertificationBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = PhotoImageAdapter(requireContext(), imageResources)
        binding.gvGoaldetailCertification.adapter = adapter

        binding.gvGoaldetailCertification.setOnItemClickListener { parent, view, position, id ->
            val intent = Intent(requireContext(), PhotoDetailActivity::class.java).apply {
                putExtra("imageResource", imageResources[position])
            }
            startActivity(intent)
        }
    }
}