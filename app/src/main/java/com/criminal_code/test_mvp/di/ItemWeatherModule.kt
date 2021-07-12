package com.criminal_code.test_mvp.di

import com.criminal_code.test_mvp.model.current.IWeatherCurrent
import com.criminal_code.test_mvp.model.current.WeatherCurrent
import com.criminal_code.test_mvp.model.item.IItemWeather
import com.criminal_code.test_mvp.model.item.ItemWeather
import dagger.Binds
import dagger.Module

@Module
abstract class ItemWeatherModule {
    @Binds
    abstract fun bindItemWeather(itemWeather: ItemWeather): IItemWeather
}