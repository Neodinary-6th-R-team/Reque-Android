package com.neodinary.reque.community

import androidx.fragment.app.viewModels
import com.neodinary.reque.common.ui.base.BaseFragment
import con.neodinary.reque.community.R
import con.neodinary.reque.community.databinding.FragmentCommunityBinding

class CommunityFragment : BaseFragment<FragmentCommunityBinding, CommunityVIewModel>(){
    override val layoutResourceId: Int
        get() = R.layout.fragment_community

    override val viewModel: CommunityVIewModel by viewModels()

    override fun initView() {

    }

    override fun initDataBinding() {

    }

    override fun initObserving() {

    }
}