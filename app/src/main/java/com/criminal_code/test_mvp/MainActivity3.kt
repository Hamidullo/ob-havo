package com.criminal_code.test_mvp

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.PorterDuff
import android.location.*
import android.net.ConnectivityManager
import android.os.Build
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.criminal_code.test_mvp.model.item.entity.ItemWeatherData
import com.criminal_code.test_mvp.presenter.item.ItemWeatherPresenter
import com.criminal_code.test_mvp.view.IItemWeatherView
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.squareup.picasso.Picasso
import java.sql.DriverManager.println
import java.text.SimpleDateFormat
import java.util.*

class MainActivity3 : MvpAppCompatActivity(), IItemWeatherView {

    @InjectPresenter
    lateinit var presenter: ItemWeatherPresenter

    private var loadingIndicator: ProgressBar? = null
    private var myCity: EditText? = null

    private lateinit var fusedLocationClient: FusedLocationProviderClient
    private var city: String = "Moscow"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weather)

        loadingIndicator = findViewById<View>(R.id.loader) as ProgressBar
        loadingIndicator!!.visibility = View.VISIBLE
        loadingIndicator!!.indeterminateDrawable.setColorFilter(
                ContextCompat
                        .getColor(this, R.color.purple_500), PorterDuff.Mode.MULTIPLY)

        findViewById<LinearLayout>(R.id.mainContainer).visibility = View.GONE
        findViewById<TextView>(R.id.errorText).visibility = View.GONE

        fusedLocationClient = LocationServices.getFusedLocationProviderClient(this)

        val connMgr = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val networkInfo = connMgr.activeNetworkInfo
        if (networkInfo != null && networkInfo.isConnected) {

            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    != PackageManager.PERMISSION_GRANTED){

                ActivityCompat.requestPermissions(
                        this,
                        arrayOf(
                                Manifest.permission.ACCESS_FINE_LOCATION,
                                Manifest.permission.ACCESS_COARSE_LOCATION
                        ),
                        PackageManager.PERMISSION_GRANTED
                )

            }


            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED
                    && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION)
                    == PackageManager.PERMISSION_GRANTED) {

                fusedLocationClient.lastLocation
                        .addOnSuccessListener { location->
                            if (location != null) {
                                presenter.executeByLocation(false,location)
                            }
                        }
            } else

                ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)
                presenter.execute(false, "Moscow")
        } else {
            errorMessage("Error")
        }

        myCity = findViewById<EditText>(R.id.myCity)

        findViewById<ImageButton>(R.id.subCity).setOnClickListener {
            if (myCity!!.text.isNotEmpty())
                presenter.execute(false, myCity!!.text.toString())
            myCity!!.hideKeyboard()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.future_days -> {
                val intent = Intent(this, MainActivity2::class.java)
                        .putExtra("city",city)
                startActivity(intent)
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    @RequiresApi(Build.VERSION_CODES.CUPCAKE)
    private fun View.hideKeyboard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(windowToken, 0)
    }

    @SuppressLint("SetTextI18n")
    override fun setDataView(itemWeatherData: ItemWeatherData) {

        findViewById<LinearLayout>(R.id.mainContainer).visibility = View.VISIBLE

        findViewById<TextView>(R.id.address).text = itemWeatherData.name + ", " + itemWeatherData.sys!!.country
        city = itemWeatherData.name.toString().split(" ")[0]
        findViewById<TextView>(R.id.updated_at).text =  SimpleDateFormat("dd/MM/yyyy hh:mm a", Locale.ENGLISH)
                .format(Date(itemWeatherData.dt * 1000))
        findViewById<TextView>(R.id.status).text = itemWeatherData.weather[0].description!!.capitalize()
        findViewById<TextView>(R.id.temp).text = itemWeatherData.main!!.temp.toString() + "°C"
        findViewById<TextView>(R.id.temp_min).text = itemWeatherData.main!!.temp_min.toString() + "°C"
        findViewById<TextView>(R.id.temp_max).text = itemWeatherData.main!!.temp_max.toString() + "°C"
        findViewById<TextView>(R.id.sunrise).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
                .format(Date(itemWeatherData.sys!!.sunrise * 1000))
        findViewById<TextView>(R.id.sunset).text = SimpleDateFormat("hh:mm a", Locale.ENGLISH)
                .format(Date(itemWeatherData.sys!!.sunset * 1000))
        findViewById<TextView>(R.id.wind).text = itemWeatherData.wind!!.speed.toString()
        findViewById<TextView>(R.id.pressure).text = itemWeatherData.main!!.pressure.toString()
        findViewById<TextView>(R.id.humidity).text = itemWeatherData.main!!.humidity.toString()

        val iconUrl = "http://openweathermap.org/img/wn/" + itemWeatherData.weather[0].icon + "@2x.png"
        //  http://openweathermap.org/img/wn/01d@2x.png

        println(iconUrl)

        Picasso.with(this).load(iconUrl).error(R.drawable.error).into(findViewById<ImageView>(R.id.weatherIcon))
    }

    override fun errorMessage(message: String) {
        loadingIndicator?.visibility = View.GONE
        findViewById<LinearLayout>(R.id.mainContainer).visibility = View.GONE
    }

    override fun hideLoadingIndicator() {
        loadingIndicator?.visibility = View.GONE
        findViewById<TextView>(R.id.errorText).visibility = View.GONE
    }
}