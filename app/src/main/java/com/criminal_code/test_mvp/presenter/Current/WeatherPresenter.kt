package com.criminal_code.test_mvp.presenter.Current

import android.app.Application
import android.content.Context
import android.content.Intent
import android.location.Location
import android.os.Bundle
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.criminal_code.test_mvp.MainActivity4
import com.criminal_code.test_mvp.di.DaggerUserComponent
import com.criminal_code.test_mvp.di.UserComponent
import com.criminal_code.test_mvp.model.current.WeatherCurrent
import com.criminal_code.test_mvp.model.current.entity.ListWeather
import com.criminal_code.test_mvp.model.current.entity.WeatherCurrentData
import com.criminal_code.test_mvp.view.IWeatherView
import es.dmoral.toasty.Toasty
import rx.Observable
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers
import java.util.*
import javax.inject.Inject

@InjectViewState
class WeatherPresenter(): MvpPresenter<IWeatherView>() {
    /*//val CITY: String = "Tashkent,uz"
    //val API: String = "87371da9baf6be746254d014e4eac004"
    //  https://api.openweathermap.org/data/2.5/forecast?q=Toshkent&units=metric&appid=87371da9baf6be746254d014e4eac004"*/

    @Inject
    lateinit var weatherCurrent: WeatherCurrent

    private var dataWeather: WeatherCurrentData = WeatherCurrentData()

    fun execute(isUpdate: Boolean,city: String) {

        val component: UserComponent = DaggerUserComponent.builder().email("").password("").build()

        weatherCurrent = component.getWeatherCurrent()

        val dataObservable = weatherCurrent.getWeatherCurrent(city)

        dataObservable!!.subscribeOn(Schedulers.newThread())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe { weatherCurrentData: WeatherCurrentData? ->
                dataWeather = weatherCurrentData!!
                val weathers: MutableList<ListWeather?> = ArrayList()
                weathers.addAll(weatherCurrentData.list)
                viewState.hideLoadingIndicator()
                when {
                    weathers.isEmpty() -> viewState.setEmptyResponseText("There is no information")
                    isUpdate -> viewState.updateWeatherListView(weathers)
                    else -> viewState.setWeatherListViewData(weathers)
                }
            }
    }

    fun toItemWeather(application: Context, position: Int): Intent {

        val bundle = Bundle()
        bundle.putString("address", dataWeather.city!!.name.toString() + ", " + dataWeather.city!!.country.toString())
        bundle.putString("time", dataWeather.list[position].dt_txt.toString())
        bundle.putDouble("temp", dataWeather.list[position].main!!.temp!!)
        bundle.putDouble("tempmin", dataWeather.list[position].main!!.temp_min!!)
        bundle.putDouble("tempmax", dataWeather.list[position].main!!.temp_max!!)
        bundle.putString("description", dataWeather.list[position].weather[0].description.toString())
        bundle.putString("icon", dataWeather.list[position].weather[0].icon.toString())
        bundle.putLong("sunrise", dataWeather.city!!.sunrise!!)
        bundle.putLong("sunset", dataWeather.city!!.sunset!!)
        bundle.putString("wind", dataWeather.list[position].wind!!.speed.toString())
        bundle.putString("pressure", dataWeather.list[position].main!!.pressure.toString())
        bundle.putString("humidity", dataWeather.list[position].main!!.humidity.toString())
        bundle.putString("population", dataWeather.city!!.population.toString())

        val intent = Intent(application, MainActivity4::class.java)
        intent.putExtra("all",bundle)
        return intent
    }

}

