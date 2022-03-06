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
import java.sql.DriverManager.println
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
        val timeTextView = convertView.findViewById<View>(R.id.time) as TextView
        val descriptionTextView = convertView.findViewById<View>(R.id.description) as TextView
        val maxTempTextView = convertView.findViewById<View>(R.id.max_temp) as TextView
        val minTempTextView = convertView.findViewById<View>(R.id.min_tem) as TextView
        val iconImageView = convertView.findViewById<View>(R.id.icon) as ImageView

        val temp = weather!!.main!!.temp.toString()
        tempTextView.text = temp

        val dateAndTime = weather.dt_txt.toString()
        val date: String = dateAndTime.subSequence(0,10).toString()
        val time: String = dateAndTime.subSequence(11,16).toString()
        dateTextView.text = date//weather.dt_txt
        timeTextView.text = time
        descriptionTextView.text = weather.weather[0]!!.description

        maxTempTextView.text = weather.main!!.temp_max.toString()
        minTempTextView.text = weather.main!!.temp_min.toString()

        val iconUrl = "https://openweathermap.org/img/wn/" + weather.weather[0]!!.icon + "@2x.png"
        //  http://openweathermap.org/img/wn/01d@2x.png

        println(iconUrl)

        Picasso.get().load(iconUrl).error(R.drawable.error).into(iconImageView)

        return convertView
    }
}