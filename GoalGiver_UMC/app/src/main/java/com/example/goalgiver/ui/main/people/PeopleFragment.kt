package com.example.goalgiver.ui.main.people

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goalgiver.databinding.FragmentPeopleBinding

class PeopleFragment: Fragment() {
    lateinit var binding: FragmentPeopleBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPeopleBinding.inflate(inflater, container, false)

        initFriendList()

        return binding.root
    }

    private fun initFriendList() {
        val rv_friend = binding.rvPeopleFriendlist
        val itemList = ArrayList<FriendItem>()
        itemList.add(FriendItem("햄", 123))
        itemList.add(FriendItem("고갼", 123))
        itemList.add(FriendItem("해리", 123))

        val friendListAdapter = FriendListAdapter(itemList)
        friendListAdapter.notifyDataSetChanged()

        rv_friend.adapter = friendListAdapter
        rv_friend.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
    }
}