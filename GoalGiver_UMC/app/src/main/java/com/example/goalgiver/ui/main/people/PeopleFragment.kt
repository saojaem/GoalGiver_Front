package com.example.goalgiver.ui.main.people


import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.goalgiver.R
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
        setSearchBtn()

        return binding.root
    }

    private fun initFriendList() {



        val rv_friend = binding.rvPeopleFriendlist
        val itemList = ArrayList<FriendItem>()
        itemList.add(FriendItem("햄", R.drawable.richard_addgoal))
        itemList.add(FriendItem("고갼", R.drawable.richard_addgoal))
        itemList.add(FriendItem("해리", R.drawable.richard_addgoal))


        // 데이터 저장
        val sharedPreferences = requireContext().getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        for (i in itemList.indices) {
            editor.putString("friend${i + 1}_name", itemList[i].name)
            editor.putInt("friend${i + 1}_img", itemList[i].profileImg)
        }
        editor.apply()

        val friendListAdapter = FriendListAdapter(itemList)
        rv_friend.adapter = friendListAdapter
        rv_friend.layoutManager = LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)


    }

    private fun setSearchBtn() {
        binding.btnPeopleSearch.setOnClickListener {
            startActivity(Intent(requireActivity(), SearchFriendActivity::class.java))
        }
    }
}