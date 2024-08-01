package com.example.goalgiver.ui.main.mypage

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.example.goalgiver.R
import com.example.goalgiver.databinding.FragmentMypageBinding
import com.example.goalgiver.ui.main.login.LoginFragment

class MypageFragment : Fragment() {
    lateinit var binding: FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)

        binding.giveList.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, GiveListFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN)
                .addToBackStack(null)
                .commit()
        }
        // Navigate to LoginFragment when logout is clicked
        binding.logout.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, LoginFragment())
                .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .addToBackStack(null)
                .commit()
        }


        return binding.root
    }
}
