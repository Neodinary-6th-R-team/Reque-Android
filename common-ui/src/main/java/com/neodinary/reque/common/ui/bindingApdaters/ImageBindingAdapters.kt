package com.neodinary.reque.common.ui.bindingApdaters

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter

object ImageBindingAdapters {
    @JvmStatic
    @BindingAdapter("imgUri")
    fun ImageView.setImageUri(uri: Uri?){
        uri?.let {
            this.setImageURI(it)
        }
    }
}