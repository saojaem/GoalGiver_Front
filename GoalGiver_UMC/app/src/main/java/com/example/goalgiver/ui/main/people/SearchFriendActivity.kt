package com.example.goalgiver.ui.main.people

import android.os.Bundle
import android.widget.LinearLayout
import android.widget.SearchView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.goalgiver.R

class SearchFriendActivity: AppCompatActivity() {
    val TAG = "SearchFriendActivity"

    lateinit var rv_friend_list: RecyclerView
    lateinit var friendListAdapter: SearchFriendListAdapter
    lateinit var persons: ArrayList<Person>
    lateinit var search_view_friend_list: SearchView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_friendsearch)

        rv_friend_list = findViewById(R.id.rv_search_friendlist)
        search_view_friend_list = findViewById(R.id.search_view_friend)
        search_view_friend_list.setOnQueryTextListener(searchViewTextListener)

        persons = tempPersons()
        setAdapter()
    }

    var searchViewTextListener: SearchView.OnQueryTextListener =
        object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return false
            }

            override fun onQueryTextChange(s: String): Boolean {
                //SearchFriendListAdapter.filter.filter(s)
                friendListAdapter.filter.filter(s)
                return false
            }
        }

    fun setAdapter() {
        rv_friend_list.layoutManager = LinearLayoutManager(this)
        friendListAdapter = SearchFriendListAdapter(persons, this)
        rv_friend_list.adapter = friendListAdapter
    }

    fun tempPersons(): ArrayList<Person> {
        var tempPersons = ArrayList<Person>()
        tempPersons.add(Person("kim"))
        tempPersons.add(Person("lee"))
        tempPersons.add(Person("park"))
        tempPersons.add(Person("hong"))
        tempPersons.add(Person("hwang"))
        tempPersons.add(Person("sim"))
        return tempPersons
    }
}