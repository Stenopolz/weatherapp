package com.stenopolz.weatherapp.view

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.content.res.AppCompatResources
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.DividerItemDecoration
import com.stenopolz.weatherapp.R
import com.stenopolz.weatherapp.databinding.FragmentForecastListBinding
import com.stenopolz.weatherapp.viewmodel.ForecastListViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class ForecastListFragment : Fragment() {

    private var _binding: FragmentForecastListBinding? = null
    private val binding
        get() = requireNotNull(_binding) {
            "Binding is only valid between onCreateView and onDestroyView"
        }
    private val forecastListViewModel: ForecastListViewModel by viewModels()
    private val weatherAdapter: WeatherAdapter = WeatherAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentForecastListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        forecastListViewModel.fetchWeather()

        binding.apply {
            lifecycleOwner = viewLifecycleOwner
            adapter = weatherAdapter
            viewModel = forecastListViewModel
            list.addItemDecoration(
                DividerItemDecoration(
                    requireContext(),
                    DividerItemDecoration.VERTICAL
                ).apply {
                    AppCompatResources.getDrawable(requireContext(), R.drawable.divider)?.let {
                        setDrawable(it)
                    }
                }
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
