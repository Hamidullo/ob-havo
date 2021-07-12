package com.criminal_code.test_mvp.presenter.item

import android.location.Location
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.criminal_code.test_mvp.di.DaggerUserComponent
import com.criminal_code.test_mvp.di.UserComponent
import com.criminal_code.test_mvp.model.current.WeatherCurrent
import com.criminal_code.test_mvp.model.current.entity.ListWeather
import com.criminal_code.test_mvp.model.current.entity.WeatherCurrentData
import com.criminal_code.test_mvp.model.item.ItemWeather
import com.criminal_code.test_mvp.model.item.entity.ItemWeatherData
import com.criminal_code.test_mvp.view.IItemWeatherView
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.ArrayList
import javax.inject.Inject

@InjectViewState
class ItemWeatherPresenter() : MvpPresenter<IItemWeatherView>() {
    //  https://api.openweathermap.org/data/2.5/weather?q=Tashkent,uz&units=metric&appid=87371da9baf6be746254d014e4eac004"

    @Inject
    lateinit var weatherItem: ItemWeather

    init {
        val component: UserComponent = DaggerUserComponent.builder().email("").password("").build()

        weatherItem = component.getItemWeather()
    }

    fun execute(isUpdate: Boolean, city: String) {

        val dataObservable = weatherItem.getWeatherItem(city)

        dataObservable!!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { weatherItemData: ItemWeatherData? ->

                    viewState.hideLoadingIndicator()
                    when {
                        weatherItemData!!.name == null -> viewState.errorMessage("There is no information")
                        isUpdate -> viewState.setDataView(weatherItemData)
                        else -> viewState.setDataView(weatherItemData)
                    }
                }
    }

    fun executeByLocation(isUpdate: Boolean, location: Location) {

        val dataObservable = weatherItem.getWeatherItemByLocation(location)

        dataObservable!!.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe { weatherItemData: ItemWeatherData? ->
                    viewState.hideLoadingIndicator()
                    when {
                        weatherItemData!!.name == null -> viewState.errorMessage("There is no information")
                        isUpdate -> viewState.setDataView(weatherItemData)
                        else -> viewState.setDataView(weatherItemData)
                    }
                }
    }


}
