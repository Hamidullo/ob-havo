package com.criminal_code.test_mvp.view

import com.arellomobile.mvp.MvpView
import com.criminal_code.test_mvp.model.current.entity.ListWeather
import com.criminal_code.test_mvp.model.current.entity.WeatherCurrentData

interface IWeatherView: MvpView {
    fun setWeatherListViewData(earthquakes: List<ListWeather?>?)
    fun updateWeatherListView(earthquakes: List<ListWeather?>?)
    fun setEmptyResponseText(text: String?)
    fun hideLoadingIndicator()
    fun showNoConnectionMessage()
}