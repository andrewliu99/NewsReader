package com.andrew.newsreader.util

import android.widget.ImageView
import com.andrew.newsreader.contextRef
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions

object ImageUtils {

    private fun getRoundRectOption(radius: Int): RequestOptions {
        return RequestOptions()
            .transform(MultiTransformation(CenterCrop(), RoundedCorners(radius)))
            .priority(Priority.NORMAL)
    }

    fun getRoundRectImageFromUrl(src: String, view: ImageView, radius: Int) {

        if (radius > 0) {
            Glide.with(contextRef?.get()!!)
                .load(src)
                .apply(getRoundRectOption(radius))
                .into(view)
        } else {
            Glide.with(contextRef?.get()!!)
                .load(src)
                .into(view)
        }
    }
}