package com.neodinary.reque.data.util

import okhttp3.MediaType.Companion.toMediaTypeOrNull
import okhttp3.MultipartBody
import okhttp3.RequestBody.Companion.asRequestBody
import java.io.File

internal object RetrofitUtility {
    fun getProfileMultipartData(name : String, file : File?): MultipartBody.Part? {
        return file?.let {
            val mediaType = "image/*".toMediaTypeOrNull()
            val requestFile = file.asRequestBody(mediaType)
            MultipartBody.Part.createFormData(name, file.name, requestFile)
        }
    }
}