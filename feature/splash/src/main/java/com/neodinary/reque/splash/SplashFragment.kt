package com.neodinary.reque.splash

import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.neodinary.reque.common.ui.base.BaseFragment
import com.neodinary.reque.common.ui.base.NavigationEvent
import com.neodinary.reque.splash.databinding.FragmentSplashBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class SplashFragment : BaseFragment<FragmentSplashBinding, SplashViewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.fragment_splash

    override val viewModel: SplashViewModel by viewModels()

    override fun initView() {}

    override fun initDataBinding() {}

    override fun initObserving() {
        viewLifecycleOwner.lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.action.collect {action ->
                    when(action){
                        SplashAction.NavigateToLogin -> {
                            (activity as NavigationEvent).navigateSplashToLogin()
                        }
                    }
                }
            }
        }
    }
}