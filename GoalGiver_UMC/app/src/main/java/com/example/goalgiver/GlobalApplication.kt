package com.example.goalgiver

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "3d4bf94b72b5b0d456dce8aba5f0623c")
    }
}