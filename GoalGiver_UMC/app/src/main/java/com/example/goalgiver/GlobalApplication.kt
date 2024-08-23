package com.example.goalgiver

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class GlobalApplication: Application() {
    override fun onCreate() {
        super.onCreate()

        KakaoSdk.init(this, "dcbece26a1b1dfa37cbe592e728ea3c2")
    }
}