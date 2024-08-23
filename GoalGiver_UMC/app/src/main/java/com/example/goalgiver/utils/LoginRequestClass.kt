package com.example.goalgiver.utils

data class LoginResponseClass(
    val message: String,
    val user: User
)

data class User(
    val kakaoId: Long,
    val email: String,
    val profileImage: String,
    val accessToken: String,
    val refreshToken: String,
    val nickname: String?
)
