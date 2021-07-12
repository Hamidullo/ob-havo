package com.criminal_code.test_mvp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.criminal_code.test_mvp.R
import com.criminal_code.test_mvp.model.current.entity.ListWeather
import com.squareup.picasso.Picasso
import java.util.*

class Adapter(context: Context?, weathers: List<ListWeather?>?) : ArrayAdapter<ListWeather?>(
    context!!,
    0,
    weathers!!
) {

    private val placeStep = 2
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var convertView = convertView
        val weather = getItem(position)
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item, parent, false)
        }
        val tempTextView = convertView!!.findViewById<View>(R.id.temp) as TextView
        val dateTextView = convertView.findViewById<View>(R.id.date) as TextView
        val descriptionTextView = convertView.findViewById<View>(R.id.description) as TextView
        val maxTempTextView = convertView.findViewById<View>(R.id.max_temp) as TextView
        val minTempTextView = convertView.findViewById<View>(R.id.min_tem) as TextView
        val iconImageView = convertView.findViewById<View>(R.id.icon) as ImageView

        val temp = weather!!.main!!.temp.toString()
        tempTextView.text = temp

        dateTextView.text = weather.dt_txt
        descriptionTextView.text = weather.weather[0]!!.description

        maxTempTextView.text = weather.main!!.temp_max.toString()
        minTempTextView.text = weather.main!!.temp_min.toString()

        val iconUrl = "http://openweathermap.org/img/wn/" + weather.weather[0]!!.icon + "@2x.png"
        //  http://openweathermap.org/img/wn/01d@2x.png

        println(iconUrl)

        Picasso.with(context).load(iconUrl).error(R.drawable.sunrise).into(iconImageView)

        return convertView
    }
}