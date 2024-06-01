package com.neodinary.reque.common.ui.dialog

import com.neodinary.reque.common.R
import com.neodinary.reque.common.databinding.DialogLoadingBinding
import com.neodinary.reque.common.ui.base.BaseDialogFragment

class LoadingDialogFragment : BaseDialogFragment<DialogLoadingBinding>(){
    override val layoutResourceId: Int
        get() = R.layout.dialog_loading

    override fun initDataBinding() {}
    override fun initView() {}

}