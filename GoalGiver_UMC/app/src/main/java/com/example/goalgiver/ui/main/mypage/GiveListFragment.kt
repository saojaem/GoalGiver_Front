package com.example.goalgiver.ui.main.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.goalgiver.databinding.FragmentGivelistBinding

class GiveListFragment : Fragment() {
    lateinit var binding: FragmentGivelistBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGivelistBinding.inflate(inflater, container, false)

        // Handle back button click
        binding.giveListBack.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        return binding.root
    }
}