package com.example.goalgiver.ui.main.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.os.Message
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebView
import android.webkit.WebViewClient
import android.widget.FrameLayout
import androidx.fragment.app.Fragment
import com.example.goalgiver.R
import com.example.goalgiver.databinding.FragmentLoginBinding
import com.example.goalgiver.ui.certification.MapCertificationActivity.Companion.TAG
import com.example.goalgiver.ui.main.MainActivity
import com.example.goalgiver.ui.main.nickname.NicknameFragment
import com.example.goalgiver.utils.RetrofitClient
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.common.model.ClientError
import com.kakao.sdk.common.model.ClientErrorCause
import com.kakao.sdk.user.UserApiClient
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import okhttp3.Call
import okhttp3.Callback
import okhttp3.FormBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.Response
import java.io.IOException
import java.net.URISyntaxException

class LoginFragment : Fragment() {

    private var _binding: FragmentLoginBinding? = null
    private val binding get() = _binding!!

    private lateinit var webView: WebView
    private lateinit var webViewLayout: FrameLayout

    private val BASE_URL = "http://goalgiverr-env.eba-2ff2eph3.ap-northeast-2.elasticbeanstalk.com"
    private val kakaoClientId = "d27800dd819a34a740c37261b75fe83a" //REST API key
    private val kakaoRedirectUri = BASE_URL + "/api/auth/login/kakao-redirect"

    // 카카오 로그인
    // 카카오계정으로 로그인 공통 callback 구성
    // 카카오톡으로 로그인 할 수 없어 카카오계정으로 로그인할 경우 사용됨
    val callback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        if (error != null) {
            Log.e(TAG, "카카오계정으로 로그인 실패", error)
        } else if (token != null) {
            Log.i(TAG, "카카오계정으로 로그인 성공 ${token.accessToken}")
            GoMain()
        }
    }
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

//        binding.kakaoLoginButton.setOnClickListener {
//            CoroutineScope(Dispatchers.IO).launch {
//                startKakaoLogin()
//            }
//        }

        binding.kakaoLoginButton.setOnClickListener {
            // 카카오톡이 설치되어 있으면 카카오톡으로 로그인, 아니면 카카오계정으로 로그인
            if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
                UserApiClient.instance.loginWithKakaoTalk(requireContext()) { token, error ->
                    if (error != null) {
                        Log.e(TAG, "카카오톡으로 로그인 실패", error)

                        // 사용자가 카카오톡 설치 후 디바이스 권한 요청 화면에서 로그인을 취소한 경우,
                        // 의도적인 로그인 취소로 보고 카카오계정으로 로그인 시도 없이 로그인 취소로 처리 (예: 뒤로 가기)
                        if (error is ClientError && error.reason == ClientErrorCause.Cancelled) {
                            return@loginWithKakaoTalk
                        }

                        // 카카오톡에 연결된 카카오계정이 없는 경우, 카카오계정으로 로그인 시도
                        UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
                    } else if (token != null) {
                        Log.i(TAG, "카카오톡으로 로그인 성공 ${token.accessToken}")
                        GoMain()
                    }
                }
            } else {
                UserApiClient.instance.loginWithKakaoAccount(requireContext(), callback = callback)
            }
        }
    }

//    private fun startKakaoLogin() {
//        val client = OkHttpClient.Builder()
//            .followRedirects(false)
//            .followSslRedirects(false)
//            .build()
//
//        val request = Request.Builder()
//            .url(BASE_URL+"/api/auth/login/kakao")
//            .get()
//            .build()
//
//        try {
//            val response: Response = client.newCall(request).execute()
//
//            if (response.isRedirect) {
//                val redirectUrl = response.header("Location")
//                println("리디렉션. $redirectUrl")
//                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(redirectUrl))
//                startActivity(intent)
//            } else if (response.isSuccessful) {
//                println("성공: ${response.code}")
//            } else {
//                println("실패: ${response.code}")
//            }
//        } catch (e: IOException) {
//            e.printStackTrace()
//            println("예외")
//        }
//    }

//    private fun startKakaoLogin() {
//        val authUrl = "https://kauth.kakao.com/oauth/authorize" +
//                "?client_id=$kakaoClientId" +
//                "&redirect_uri=$kakaoRedirectUri" +
//                "&response_type=code"
//
//        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authUrl))
//        startActivity(intent)
//    }

//    override fun onResume() {
//        super.onResume()
//
//        val data: Uri? = activity?.intent?.data
//        if (data != null && data.toString().startsWith(kakaoRedirectUri)) {
//            val code = data.getQueryParameter("code")
//            if (code != null) {
//                requestAccessToken(code)
//            }
//        }
//    }


    private fun requestAccessToken(code: String) {
        Log.d("toktok", "toktook")
    }

    fun GoMain() {
        parentFragmentManager.beginTransaction()
            .replace(R.id.main_frm, NicknameFragment())
            .addToBackStack(null)
            .commitAllowingStateLoss()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}