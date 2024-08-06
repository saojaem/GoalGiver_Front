package com.example.goalgiver.ui.main.nickname

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.goalgiver.databinding.FragmentNicknameBinding
import com.example.goalgiver.ui.main.MainActivity

class NicknameFragment : Fragment() {

    private var _binding: FragmentNicknameBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNicknameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hide bottom navigation
        (activity as? MainActivity)?.binding?.mainBnv?.visibility = View.GONE

        binding.startButton.setOnClickListener {
            (activity as? MainActivity)?.showMainActivity()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}