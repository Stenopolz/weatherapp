package com.stenopolz.weatherapp.view

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.stenopolz.weatherapp.databinding.ItemWeatherInfoBinding
import com.stenopolz.weatherapp.viewmodel.WeatherUiModel

class WeatherAdapter : RecyclerView.Adapter<WeatherAdapter.ViewHolder>() {

    private var weatherList: List<WeatherUiModel> = emptyList()

    fun setWeatherList(countries: List<WeatherUiModel>) {
        weatherList = countries
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val weather = weatherList[position]
        holder.bind(weather)
    }

    override fun getItemCount(): Int {
        return weatherList.size
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            ItemWeatherInfoBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    class ViewHolder(
        private val binding: ItemWeatherInfoBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        fun bind(weatherInfo: WeatherUiModel) {
            binding.weatherInfo = weatherInfo
        }
    }
}
