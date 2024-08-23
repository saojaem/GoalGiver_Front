package com.example.goalgiver.utils

import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Query
import retrofit2.http.Url

interface ApiService {
    @GET("api/auth/login/kakao")
    fun kakaoLogin(@Query("access_token") accessToken: String): Call<LoginResponse>
}

data class LoginResponse(
    val message: String,
    val user: User
)

//data class User(
//    val kakaoId: Long,
//    val email: String,
//    val profileImage: String,
//    val accessToken: String,
//    val refreshToken: String,
//    val nickname: String?
//)