package com.example.goalgiver.ui.goaldetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goalgiver.databinding.FragmentGoaldetailTeamprogressBinding

class TeamProgressFragment: Fragment() {

    lateinit var binding: FragmentGoaldetailTeamprogressBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentGoaldetailTeamprogressBinding.inflate(inflater, container, false)

        setTeamRV()

        return binding.root
    }

    private fun setTeamRV() {
        val rv_team = binding.rvTeamprogress
        val itemList = ArrayList<TeamProgressItem>()

        itemList.add(TeamProgressItem("레나", 50000, 50))
        itemList.add(TeamProgressItem("리처드", 30000, 100))

        val teamProgressAdapter = TeamProgressAdapter(itemList)
        teamProgressAdapter.notifyDataSetChanged()

        rv_team.adapter = teamProgressAdapter
        rv_team.layoutManager = LinearLayoutManager(requireContext())
    }
}