package com.criminal_code.test_mvp

import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.View
import android.widget.ListView
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.criminal_code.test_mvp.adapter.Adapter
import com.criminal_code.test_mvp.model.current.entity.ListWeather
import com.criminal_code.test_mvp.presenter.Current.WeatherPresenter
import com.criminal_code.test_mvp.view.IWeatherView

class MainActivity2 : MvpAppCompatActivity(),IWeatherView {

    @InjectPresenter
    lateinit var _presenter: WeatherPresenter

    private var _weathers: List<ListWeather>? = null

    private var __weathersListView: ListView? = null
    private var _errorTextView: TextView? = null
    private var _adapter: Adapter? = null
    private var _loadingIndicator: ProgressBar? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        __weathersListView = findViewById<View>(R.id.list_view) as ListView

        _errorTextView = findViewById<View>(R.id.errorTextView) as TextView
        __weathersListView!!.emptyView = _errorTextView

        _loadingIndicator = findViewById<View>(R.id.loading_spinner) as ProgressBar
        _loadingIndicator!!.indeterminateDrawable.setColorFilter(
            ContextCompat
                .getColor(this, R.color.purple_500), PorterDuff.Mode.MULTIPLY
        )

        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo

        if (networkInfo != null && networkInfo.isConnected) {

            var city: String = "Moscow"
            if (intent.getStringExtra("city") != null){
                city = intent.getStringExtra("city")!!
            }
            _presenter.execute(false, city)
        } else {
            showNoConnectionMessage()
        }

        __weathersListView!!.setOnItemClickListener { parent, view, position, id ->
            startActivity(_presenter.toItemWeather(applicationContext,position))
        }
    }

    override fun setWeatherListViewData(weathers: List<ListWeather?>?) {
        _weathers = weathers as List<ListWeather>?
        _adapter = Adapter(applicationContext, _weathers)
        __weathersListView!!.adapter = _adapter
        __weathersListView!!.dividerHeight = 2
    }

    override fun updateWeatherListView(earthquakes: List<ListWeather?>?) {
        _weathers = earthquakes as List<ListWeather>?
        _adapter?.notifyDataSetChanged()
    }

    override fun setEmptyResponseText(text: String?) {
        _errorTextView?.text = text
    }

    override fun hideLoadingIndicator() {
        _loadingIndicator?.visibility = View.GONE
    }

    override fun showNoConnectionMessage() {
        _loadingIndicator?.visibility = View.GONE;
        _errorTextView?.setText(R.string.error);
    }


}