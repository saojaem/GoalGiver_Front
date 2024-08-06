package com.example.goalgiver.ui.main.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.goalgiver.R
import com.example.goalgiver.databinding.FragmentLoginBinding
import com.example.goalgiver.ui.main.MainActivity
import com.example.goalgiver.ui.main.nickname.NicknameFragment

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLoginBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Hide bottom navigation
        (activity as? MainActivity)?.binding?.mainBnv?.visibility = View.GONE

        binding.googleLoginButton.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.main_frm, NicknameFragment())
                .addToBackStack(null)
                .commitAllowingStateLoss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}