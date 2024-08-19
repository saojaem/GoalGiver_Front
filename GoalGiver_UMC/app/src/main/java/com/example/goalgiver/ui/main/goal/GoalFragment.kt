package com.example.goalgiver.ui.main.goal

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goalgiver.R
import com.example.goalgiver.databinding.FragmentGoalBinding
import com.example.goalgiver.ui.goaldetail.GoalDetailActivity

class GoalFragment : Fragment() {

    private var _binding: FragmentGoalBinding? = null
    private val binding get() = _binding!!

    private lateinit var goalList: ArrayList<GoalSetItem>
    private lateinit var teamChooseLauncher: ActivityResultLauncher<Intent>

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

        // ActivityResultLauncher 초기화
        teamChooseLauncher = registerForActivityResult(
            ActivityResultContracts.StartActivityForResult()
        ) { result ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null) {
                val goalItem: GoalSetItem? = result.data?.getParcelableExtra("goalItem")

                if (goalItem == null) {
                    Log.e("GoalFragment", "GoalSetItem is null!")
                } else {
                    Log.d("GoalFragment", "Received goalTitle=" + goalItem.goalTitle)
                    goalList.add(goalItem)
                    binding.goalFragmentRecyclerView.adapter?.notifyDataSetChanged()
                }
            }
        }

        binding.floatingActionButton.setOnClickListener {
            val intent = Intent(requireContext(), AddGoalMain::class.java)
            teamChooseLauncher.launch(intent)
        }

        goalList = arrayListOf(
            GoalSetItem("🎯", "Goal 1", "D-10", "100", "Progress 50%", 50)
        )

        setupRecyclerView()
    }

    private fun setupButtonListeners() {
        binding.personalButton.setOnClickListener {
            updateButtonSelection(binding.personalButton, binding.teamButton)
        }

        binding.teamButton.setOnClickListener {
            updateButtonSelection(binding.teamButton, binding.personalButton)
        }

        binding.inProgressButton.setOnClickListener {
            updateButtonSelection(binding.inProgressButton, binding.completedButton)
        }

        binding.completedButton.setOnClickListener {
            updateButtonSelection(binding.completedButton, binding.inProgressButton)
        }
    }

    private fun updateButtonSelection(selectedButton: Button, otherButton: Button) {
        selectedButton.setBackgroundResource(R.drawable.circle_choose_btn)
        selectedButton.setTextColor(resources.getColor(R.color.white))
        otherButton.setBackgroundResource(R.drawable.circle_btn)
        otherButton.setTextColor(resources.getColor(R.color.textcolor))
    }

    private fun setupRecyclerView() {
        val adapter = GoalSetAdapter(requireContext(), goalList)
        binding.goalFragmentRecyclerView.adapter = adapter
        binding.goalFragmentRecyclerView.layoutManager = LinearLayoutManager(requireContext())

        // adapter itemClick -ham
        adapter.setOnItemClickListener(object : GoalSetAdapter.OnItemClickListener {
            override fun onItemClick(position: Int) {
                val clickedItem = goalList[position]
                Log.d("GoalFragment", "Clicked: ${clickedItem}")
                val intent = Intent(requireContext(), GoalDetailActivity::class.java)
                intent.putExtra("goalItem", clickedItem)
                startActivity(intent)
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
