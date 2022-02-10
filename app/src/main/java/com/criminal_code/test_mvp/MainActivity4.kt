package com.criminal_code.test_mvp

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso
import java.sql.DriverManager.println
import java.text.SimpleDateFormat
import java.util.*

class MainActivity4 : AppCompatActivity() {
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main4)

        findViewById<TextView>(R.id.addressF).text = intent.getBundleExtra("all")!!.getString("address")
        findViewById<TextView>(R.id.updated_atF).text = intent.getBundleExtra("all")!!.getString("time")

        findViewById<TextView>(R.id.tempF).text = intent.getBundleExtra("all")!!.getDouble("temp").toString() + "°C"
        findViewById<TextView>(R.id.temp_maxF).text = "Max Temp: " + intent.getBundleExtra("all")!!.getDouble("tempmax").toString() + " °C"
        findViewById<TextView>(R.id.temp_minF).text = "Max Temp: " + intent.getBundleExtra("all")!!.getDouble("tempmin").toString() + " °C"
        findViewById<TextView>(R.id.statusF).text = intent.getBundleExtra("all")!!.getString("description")//!!.capitalize(Locale.ROOT)
        findViewById<TextView>(R.id.sunriseF).text =
            SimpleDateFormat("hh:mm a", Locale.ENGLISH)
                .format(Date(intent.getBundleExtra("all")!!.getLong("sunrise")*1000))

        findViewById<TextView>(R.id.sunsetF).text =
            SimpleDateFormat("hh:mm a", Locale.ENGLISH)
                .format(Date(intent.getBundleExtra("all")!!.getLong("sunset")*1000))

        findViewById<TextView>(R.id.windF).text = intent.getBundleExtra("all")!!.getString("wind")

        findViewById<TextView>(R.id.pressureF).text = intent.getBundleExtra("all")!!.getString("pressure") + " hPa"

        findViewById<TextView>(R.id.humidityF).text = intent.getBundleExtra("all")!!.getString("humidity") + " %"
        findViewById<TextView>(R.id.about).text = intent.getBundleExtra("all")!!.getString("population")

        val iconUrl = "http://openweathermap.org/img/wn/" + intent.getBundleExtra("all")!!.getString("icon") + "@2x.png"

        println(iconUrl)

        Picasso.with(this).load(iconUrl).error(R.drawable.sunrise).into(findViewById<ImageView>(R.id.weatherIconF))

    }
}