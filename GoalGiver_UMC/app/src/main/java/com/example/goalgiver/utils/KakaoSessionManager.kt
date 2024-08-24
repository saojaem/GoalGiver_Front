package com.example.goalgiver.utils

object KakaoSessionManager {
    var accessToken: String? = null

    fun updateAccessToken(newToken: String) {
        accessToken = newToken
    }

    fun getAccessToken(): String? {
        return accessToken
    }
}