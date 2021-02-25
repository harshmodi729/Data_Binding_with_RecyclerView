package com.harsh.databindingwithrecyclerview.extension

import androidx.appcompat.widget.AppCompatImageView
import androidx.databinding.BindingAdapter
import coil.load
import coil.request.CachePolicy
import coil.transform.RoundedCornersTransformation
import com.bumptech.glide.Glide

/**
 * If @BindingAdapter annotation method is create in top level like in extension or in object then
 * @JvmStatic annotation is not required.
 */
@BindingAdapter("newsImage")
fun AppCompatImageView.getNewsImage(url: String) {
    Glide.with(context)
        .load(url)
        .skipMemoryCache(false)
        .centerCrop()
        .into(this)
}

@BindingAdapter("fullImage")
fun AppCompatImageView.getNewsFullImage(url: String) {
    Glide.with(context)
        .load(url)
        .skipMemoryCache(false)
        .fitCenter()
        .into(this)
}

@BindingAdapter("coilImage")
fun AppCompatImageView.getCoilImage(url: String) {
    this.load(url) {
        crossfade(true)
        crossfade(500)
        memoryCachePolicy(CachePolicy.ENABLED)
        transformations(RoundedCornersTransformation(50F, 10F, 50F, 50F))
    }
}