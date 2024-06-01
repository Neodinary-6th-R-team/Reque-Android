package com.neodinary.reque.common.ui.bindingApdaters

import android.net.Uri
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import jp.wasabeef.glide.transformations.BlurTransformation
import java.io.File

object ImageBindingAdapters {
    @JvmStatic
    @BindingAdapter("imgUri")
    fun ImageView.setImageUri(uri: Uri?) {
        uri?.let {
            this.setImageURI(it)
        }
    }

    @JvmStatic
    @BindingAdapter("blurImg")
    fun ImageView.setBlurImg(uri: Uri?) {
        uri?.let {
            Glide.with(this)
                .load(File(it.path))
                .apply(RequestOptions.bitmapTransform(BlurTransformation(25,3)))
                .into(this)
        }
    }
}