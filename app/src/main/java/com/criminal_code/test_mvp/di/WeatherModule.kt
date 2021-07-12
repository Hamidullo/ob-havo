package com.criminal_code.test_mvp.di

import com.criminal_code.test_mvp.model.User.IUser
import com.criminal_code.test_mvp.model.User.User
import com.criminal_code.test_mvp.model.current.IWeatherCurrent
import com.criminal_code.test_mvp.model.current.WeatherCurrent
import dagger.Binds
import dagger.Module

@Module
abstract class WeatherModule {
    @Binds
    abstract fun bindWeatherCurrent(weatherCurrent: WeatherCurrent): IWeatherCurrent
}