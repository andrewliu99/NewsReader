package com.andrew.newsreader.util

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.databinding.BindingMethods
import com.andrew.newsreader.util.ImageUtils.getRoundRectImageFromUrl

@BindingMethods
object DataBindingAdapter {

    @BindingAdapter("imageUrl")
    @JvmStatic
    fun imageUrl(imgView: ImageView, url: String?) {
        if (url == null || url.isEmpty()) return
        getRoundRectImageFromUrl(url, imgView, 10)
    }
}