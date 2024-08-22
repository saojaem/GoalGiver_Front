package com.example.goalgiver.ui.main.nickname

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.example.goalgiver.R
import com.example.goalgiver.databinding.FragmentNicknameBinding
import com.example.goalgiver.ui.main.MainActivity
import com.example.goalgiver.ui.main.mypage.MypageFragment

class NicknameFragment : Fragment() {

    private var _binding: FragmentNicknameBinding? = null
    private val binding get() = _binding!!

    private var isNicknameValid = false // 닉네임 유효성 여부를 저장하는 변수

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNicknameBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // StartButton 초기 비활성화 및 색상 설정
        binding.startButton.isEnabled = false
        binding.startButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.brand_blue_100))

        // CheckDuplicateButton 클릭 리스너 설정
        binding.checkDuplicateButton.setOnClickListener {
            val nickname = binding.nicknameEditText.text.toString()

            // 닉네임 중복 확인 로직 (여기서는 예시로 간단하게 처리)
            if (nickname.isNotBlank()) {
                if (nickname == "중복된닉네임") { // 중복된 닉네임 예시
                    binding.duplicateWarningText.visibility = View.VISIBLE
                    binding.duplicateSuccessText.visibility = View.GONE
                    binding.startButton.isEnabled = false
                    binding.startButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.brand_blue_100))
                    isNicknameValid = false
                } else {
                    binding.duplicateSuccessText.visibility = View.VISIBLE
                    binding.duplicateWarningText.visibility = View.GONE
                    binding.startButton.isEnabled = true
                    binding.startButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.brand_blue)) // 활성화 시 #0E9AFF 색상 사용
                    isNicknameValid = true
                }
            } else {
                // 닉네임이 비어 있을 때 처리
                binding.duplicateWarningText.visibility = View.VISIBLE
                binding.duplicateSuccessText.visibility = View.GONE
                binding.startButton.isEnabled = false
                binding.startButton.setBackgroundColor(ContextCompat.getColor(requireContext(), R.color.brand_blue_100))
            }
        }

        // StartButton 클릭 리스너 설정
        binding.startButton.setOnClickListener {
            if (isNicknameValid) {
                val nickname = binding.nicknameEditText.text.toString()

                // 닉네임을 SharedPreferences에 저장
                val sharedPref = activity?.getPreferences(Context.MODE_PRIVATE)
                with(sharedPref?.edit()) {
                    this?.putString("user_nickname", nickname)
                    this?.apply()
                }

                // MypageFragment로 이동
                val mypageFragment = MypageFragment()

                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(com.example.goalgiver.R.id.main_frm, mypageFragment)
                    ?.commitAllowingStateLoss()

                // 바텀 네비게이션을 다시 활성화
                (activity as? MainActivity)?.showMainActivity()
            } else {
                // 비활성화 상태에서 버튼 클릭 시 Toast 메시지 표시
                Toast.makeText(requireContext(), "닉네임을 생성해주세요", Toast.LENGTH_SHORT).show()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
