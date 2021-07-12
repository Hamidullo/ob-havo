package com.criminal_code.test_mvp.view

import com.arellomobile.mvp.MvpView
import com.criminal_code.test_mvp.model.item.entity.ItemWeatherData

interface IItemWeatherView: MvpView {
    fun setDataView(itemWeatherData: ItemWeatherData)
    fun errorMessage(message: String)
    fun hideLoadingIndicator()
}