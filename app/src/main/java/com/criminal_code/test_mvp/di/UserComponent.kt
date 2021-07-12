package com.criminal_code.test_mvp.di

import com.criminal_code.test_mvp.model.User.User
import com.criminal_code.test_mvp.model.current.WeatherCurrent
import com.criminal_code.test_mvp.model.item.ItemWeather
import com.criminal_code.test_mvp.presenter.Current.WeatherPresenter
import dagger.BindsInstance
import dagger.Component
import javax.inject.Named

@Component(modules = [UserModule::class,WeatherModule::class, ItemWeatherModule::class])
interface UserComponent {
    fun getUser(): User

    fun getWeatherCurrent(): WeatherCurrent

    fun getItemWeather(): ItemWeather

    @Component.Builder
    interface Builder{

        @BindsInstance
        fun email(@Named("email") email: String): Builder

        @BindsInstance
        fun password(@Named("password") password: String): Builder

        fun build(): UserComponent
    }
}