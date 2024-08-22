package com.example.goalgiver.ui.main.mypage

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.goalgiver.databinding.FragmentMypageBinding

class MypageFragment : Fragment() {

    private lateinit var binding: FragmentMypageBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMypageBinding.inflate(inflater, container, false)

        // SharedPreferences에서 닉네임 불러오기
        val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
        val nickname = sharedPref?.getString("user_nickname", "사용자")

        // 닉네임을 UI에 설정
        binding.mypageNinkname.text = nickname

        return binding.root
    }
}
