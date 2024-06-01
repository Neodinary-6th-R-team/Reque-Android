package com.neodinary.reque.login

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.kakao.sdk.auth.model.OAuthToken
import com.kakao.sdk.user.UserApiClient
import com.neodinary.reque.common.ui.base.BaseFragment
import com.neodinary.reque.common.ui.logger.RequeLogger
import com.neodinary.reque.login.databinding.FragmentLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginFragment : BaseFragment<FragmentLoginBinding, LoginViewModel>() {
    override val layoutResourceId: Int
        get() = R.layout.fragment_login

    override val viewModel: LoginViewModel by viewModels()

    private val kakaoCallback: (OAuthToken?, Throwable?) -> Unit = { token, error ->
        RequeLogger.d(TAG, "kakaoCallback", "start")
        if (error != null) {
            RequeLogger.e(TAG, "kakaoCallback", "errorMsg = $error")
            showToast("카카오 로그인 실패")
        } else if (token != null) {
            RequeLogger.i(TAG, "kakaoCallback", "kakao token = ${token.accessToken}")
        }
        RequeLogger.d(TAG, "kakaoCallback", "end")
    }

    override fun initView() {

    }

    override fun initDataBinding() {
        binding.vm = viewModel
    }

    override fun initObserving() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.action.collect { action ->
                    when (action) {
                        is LoginAction.DoKakaoLogin -> {
                            doKakaoLogin()
                        }
                    }
                }
            }
        }
    }

    private fun doKakaoLogin() {
        if (UserApiClient.instance.isKakaoTalkLoginAvailable(requireContext())) {
            UserApiClient.instance.loginWithKakaoTalk(requireContext(), callback = kakaoCallback)
        } else {
            UserApiClient.instance.loginWithKakaoTalk(requireContext(), callback = kakaoCallback)
        }
    }

    companion object {
        private const val TAG = "LoginFragment"
    }
}