package com.neodinary.reque

import android.app.Application
import com.kakao.sdk.common.KakaoSdk

class ApplicationClass : Application() {
    override fun onCreate() {
        super.onCreate()
        KakaoSdk.init(this, BuildConfig.KAKAO_NATIVE_KEY)
    }
}