package com.stenopolz.weatherapp.view

import android.view.View
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.stenopolz.weatherapp.viewmodel.WeatherUiModel

@BindingAdapter("setData")
fun setData(recyclerView: RecyclerView, list: List<WeatherUiModel>?) {
    val adapter = recyclerView.adapter as WeatherAdapter
    adapter.setWeatherList(list ?: emptyList())
}

@BindingAdapter("setImage")
fun setImage(imageView: ImageView, imageUrl: String?) {
    Glide.with(imageView.context)
        .load(imageUrl)
        .into(imageView)
}

@BindingAdapter("visibleIf")
fun View.setVisible(isVisible: Boolean) {
    this.visibility = if (isVisible) View.VISIBLE else View.GONE
}
