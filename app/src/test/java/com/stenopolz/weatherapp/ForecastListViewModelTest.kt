package com.stenopolz.weatherapp

import com.stenopolz.weatherapp.extension.CallResult
import com.stenopolz.weatherapp.model.data.application.DispatchersHolder
import com.stenopolz.weatherapp.model.data.application.WeatherInfo
import com.stenopolz.weatherapp.model.repository.PreferencesRepository
import com.stenopolz.weatherapp.model.repository.WeatherRepository
import com.stenopolz.weatherapp.viewmodel.ForecastListViewModel
import com.stenopolz.weatherapp.viewmodel.WeatherUiModel
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Rule
import org.junit.Test
import kotlin.math.roundToInt

@OptIn(ExperimentalCoroutinesApi::class)
class ForecastListViewModelTest() {
    private val testDispatcher = StandardTestDispatcher()

    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule(testDispatcher)

    private val weatherRepository = mockk<WeatherRepository>().apply {
        coEvery { fetchWeather(any()) } returns CallResult.Success(emptyList())
    }

    private val preferencesRepository = mockk<PreferencesRepository>().apply {
        every { getCityList() } returns emptyList()
    }
    private val res = TestResourceProvider()

    private val dispatchers = DispatchersHolder(
        main = testDispatcher,
        io = testDispatcher,
        default = testDispatcher
    )

    private val testInfo = WeatherInfo(
        cityName = "test",
        temperature = 10.0f,
        minTemperature = 11.0f,
        maxTemperature = 12.0f,
        feelsLikeTemperature = 13.0f,
        description = "test description",
        imageUrl = "test image url"
    )

    private val testUiModel = WeatherUiModel(
        cityName = testInfo.cityName,
        temperature = res.getString(
            R.string.temperature_celsius_degrees_text,
            testInfo.temperature.roundToInt()
        ),
        temperatureRange = res.getString(
            R.string.temperature_range,
            res.getString(
                R.string.temperature_celsius_degrees,
                testInfo.maxTemperature.roundToInt()
            ),
            res.getString(
                R.string.temperature_celsius_degrees,
                testInfo.minTemperature.roundToInt()
            ),
            res.getString(
                R.string.temperature_celsius_degrees,
                testInfo.feelsLikeTemperature.roundToInt()
            )
        ),
        description = testInfo.description,
        imageUrl = testInfo.imageUrl
    )

    private fun getViewModel() =
        ForecastListViewModel(
            dispatchers = dispatchers,
            repository = weatherRepository,
            preferences = preferencesRepository,
            res = res
        )

    @Test
    fun `nothing is shown when on model init`() = runTest(testDispatcher) {
        // given
        val viewModel = getViewModel()

        //when
        advanceUntilIdle()

        // then
        assertEquals(false, viewModel.contentVisible.value)
        assertEquals(false, viewModel.isLoading.value)
        assertEquals(false, viewModel.isError.value)
    }

    @Test
    fun `content is shown when fetch successful`() = runTest(testDispatcher) {
        // given
        val viewModel = getViewModel()

        //when
        viewModel.fetchWeather()
        advanceUntilIdle()

        // then
        assertEquals(true, viewModel.contentVisible.value)
        assertEquals(false, viewModel.isLoading.value)
        assertEquals(false, viewModel.isError.value)
    }

    @Test
    fun `error is shown when fetch error`() = runTest(testDispatcher) {
        // given
        coEvery { weatherRepository.fetchWeather(any()) } returns CallResult.Error(
            IllegalArgumentException()
        )
        val viewModel = getViewModel()

        //when
        viewModel.fetchWeather()
        advanceUntilIdle()

        // then
        assertEquals(false, viewModel.contentVisible.value)
        assertEquals(false, viewModel.isLoading.value)
        assertEquals(true, viewModel.isError.value)
    }

    @Test
    fun `content is shown when try again successful`() = runTest(testDispatcher) {
        // given
        val viewModel = getViewModel()

        //when
        viewModel.onTryAgain()
        advanceUntilIdle()

        // then
        assertEquals(true, viewModel.contentVisible.value)
        assertEquals(false, viewModel.isLoading.value)
        assertEquals(false, viewModel.isError.value)
    }

    @Test
    fun `data is mapped correctly`() = runTest(testDispatcher) {
        // given
        coEvery { weatherRepository.fetchWeather(any()) } returns CallResult.Success(
            listOf(testInfo, testInfo, testInfo)
        )
        val viewModel = getViewModel()

        //when
        viewModel.fetchWeather()
        advanceUntilIdle()

        // then
        assertEquals(listOf(testUiModel, testUiModel, testUiModel), viewModel.weatherList.value)
    }
}
