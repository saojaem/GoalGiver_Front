package com.example.goalgiver.ui.main.goal

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.goalgiver.R
import com.example.goalgiver.databinding.FragmentGoalBinding

class GoalFragment : Fragment() {

    private var _binding: FragmentGoalBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentGoalBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        setupButtonListeners()
    }

    private fun setupButtonListeners() {
        binding.personalButton.setOnClickListener {
            updateButtonSelection(binding.personalButton, binding.teamButton)
            // 개인 버튼 클릭 시 수행할 작업
        }

        binding.teamButton.setOnClickListener {
            updateButtonSelection(binding.teamButton, binding.personalButton)
            // 팀 버튼 클릭 시 수행할 작업
        }

        binding.inProgressButton.setOnClickListener {
            updateButtonSelection(binding.inProgressButton, binding.completedButton)
            // 진행중 버튼 클릭 시 수행할 작업
        }

        binding.completedButton.setOnClickListener {
            updateButtonSelection(binding.completedButton, binding.inProgressButton)
            // 종료 버튼 클릭 시 수행할 작업
        }
    }

    private fun updateButtonSelection(selectedButton: Button, otherButton: Button) {
        selectedButton.setBackgroundResource(R.drawable.circle_choose_btn)
        selectedButton.setTextColor(resources.getColor(R.color.white))
        otherButton.setBackgroundResource(R.drawable.circle_btn)
        otherButton.setTextColor(resources.getColor(R.color.textcolor))
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
