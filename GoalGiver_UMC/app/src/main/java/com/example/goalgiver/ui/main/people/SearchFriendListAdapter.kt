package com.example.goalgiver.ui.main.people

import android.annotation.SuppressLint
import android.content.Context
import android.media.Image
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.goalgiver.R

class SearchFriendListAdapter(var persons: ArrayList<Person>, var con: Context) : RecyclerView.Adapter<SearchFriendListAdapter.ViewHolder>(), Filterable {
    var TAG = "SearchFriendListAdapter"

    var filteredPersons = ArrayList<Person>()
    var itemFilter = ItemFilter()

    inner class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        var iv_person: ImageView
        var tv_name: TextView
        var tv_plus: TextView

        init {
            iv_person = itemView.findViewById(R.id.iv_searchfriendlist_profile)
            tv_name = itemView.findViewById(R.id.tv_searchfriendlist_name)
            tv_plus = itemView.findViewById(R.id.tv_searchfriendlist_plus)

            tv_plus.setOnClickListener {
                Log.d(TAG, "plus btn clicked")
            }
        }
    }

    init {
        filteredPersons.addAll(persons)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val con = parent.context
        val inflater = con.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.item_searchfriendlist, parent, false)

        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val person: Person = filteredPersons[position]
        //holder.iv_person.setImageResource(person.imagePath)
        holder.iv_person.setImageResource(R.drawable.img_books)
        holder.tv_name.text = person.name
    }

    override fun getItemCount(): Int {
        return filteredPersons.size
    }

    //-- filter
    override fun getFilter(): Filter {
        return itemFilter
    }

    inner class ItemFilter : Filter() {
        override fun performFiltering(charSequence: CharSequence): FilterResults {
            val filterString = charSequence.toString()
            val results = FilterResults()
            Log.d(TAG, "charSequence : $charSequence")

            //검색이 필요없을 경우를 위해 원본 배열을 복제
            val filteredList: ArrayList<Person> = ArrayList<Person>()
            //공백제외 아무런 값이 없을 경우 -> 원본 배열
            if (filterString.trim { it <= ' ' }.isEmpty()) {
                results.values = persons
                results.count = persons.size

                return results
                //공백제외 2글자 이인 경우 -> 이름으로만 검색
            } else if (filterString.trim { it <= ' ' }.length <= 2) {
                for (person in persons) {
                    if (person.name.contains(filterString)) {
                        filteredList.add(person)
                    }
                }
                //그 외의 경우(공백제외 2글자 초과) -> 이름/전화번호로 검색
            } else {
                for (person in persons) {
                    if (person.name.contains(filterString)) {
                        filteredList.add(person)
                    }
                }
            }
            results.values = filteredList
            results.count = filteredList.size

            return results
        }

        @SuppressLint("NotifyDataSetChanged")
        override fun publishResults(charSequence: CharSequence?, filterResults: FilterResults) {
            filteredPersons.clear()
            filteredPersons.addAll(filterResults.values as ArrayList<Person>)
            notifyDataSetChanged()
        }
    }

}