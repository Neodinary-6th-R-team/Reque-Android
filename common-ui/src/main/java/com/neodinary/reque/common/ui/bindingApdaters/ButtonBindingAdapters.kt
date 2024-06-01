package com.neodinary.reque.common.ui.bindingApdaters

import android.view.View
import androidx.databinding.BindingAdapter
import com.neodinary.reque.common.R
import com.neodinary.reque.common.ui.view.DelayButtonClickListener

object ButtonBindingAdapters {
    @JvmStatic
    @BindingAdapter("onClick")
    fun View.setButtonOnClickListener(listener: View.OnClickListener?) {
        if (listener != null) {
            setOnClickListener(DelayButtonClickListener(listener))
        }
    }

    @JvmStatic
    @BindingAdapter("backgroundResource")
    fun View.setBackgroundResource(isSelected: Boolean) {
        if (isSelected) {
            setBackgroundResource(context.resources.getIdentifier("bg_circle_blue25", "drawable", context.packageName))
        } else {
            setBackgroundResource(0) // 배경 제거
        }
    }
}