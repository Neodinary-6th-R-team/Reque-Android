package com.neodinary.reque

import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.kakao.sdk.common.KakaoSdk
import com.kakao.sdk.common.util.Utility
import com.neodinary.reque.common.ui.base.BaseActivity
import com.neodinary.reque.databinding.ActivityMainBinding
import com.neodinary.reque.dopamine.DopamineFragment

class MainActivity : BaseActivity<ActivityMainBinding>() {
    override val layoutResourceId: Int
        get() = R.layout.activity_main

    override fun init() {
        binding.bottomNavi.itemIconTintList = null

        // 초기 프래그먼트 설정
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragmentContainer, DopamineFragment())
            .commit()

    }


}