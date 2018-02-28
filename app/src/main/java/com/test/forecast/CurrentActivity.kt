package com.test.forecast

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.support.v7.widget.CardView
import android.util.Log
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import org.json.JSONException
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.net.URLEncoder


class CurrentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
      setContentView(R.layout.activity_current)
      /*    val test: TextView = this.findViewById<TextView>(R.id.tvtest) as TextView
        test.setText("fdafd")*/


      val s1 = PreferenceManager.getDefaultSharedPreferences(this@CurrentActivity).getInt("sp1", 0)
      var ct1 = PreferenceManager.getDefaultSharedPreferences(this@CurrentActivity).getString("c1", "")
      val mcodes = getResources().getStringArray(R.array.array_codes)
      val code = mcodes[s1]

      val b = BackgroundTask(this)
      try {
        ct1 = URLEncoder.encode(ct1, "UTF8")
      } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
      }

      var Url = "http://api.openweathermap.org/data/2.5/weather?q=" + ct1 + "," + code + "&appid=6181a2306e49fb2b2df664832ff2a3e1"

      val result = b.execute(Url).get()
      val tvtemp: TextView = this.findViewById<TextView>(R.id.temp) as TextView
      val tvhumidity: TextView = this.findViewById<TextView>(R.id.humidity) as TextView
      val typressure: TextView = this.findViewById<TextView>(R.id.pressure) as TextView
      val tvheading: TextView = this.findViewById<TextView>(R.id.heading1) as TextView
      val tvheading2: TextView = this.findViewById<TextView>(R.id.heading2) as TextView
      val tvspeed: TextView = this.findViewById<TextView>(R.id.windspeed) as TextView
      val tvdeg: TextView = this.findViewById<TextView>(R.id.direction) as TextView
      val tvcity: TextView = this.findViewById<TextView>(R.id.location) as TextView
      val ivmain: ImageView = this.findViewById<ImageView>(R.id.ivmain) as ImageView
      val cardview1: CardView = this.findViewById<CardView>(R.id.card_view1) as CardView
      val cardview2: CardView = this.findViewById<CardView>(R.id.card_view2) as CardView
      val cardview3: CardView = this.findViewById<CardView>(R.id.card_view3) as CardView
      cardview1.setOnClickListener {
        PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("option", 1).apply()

        val intent = Intent(this, WeekActivity::class.java)
        this.startActivity(intent)

      }
    cardview2.setOnClickListener {
      PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("option", 2).apply()

      val intent = Intent(this, WeekActivity::class.java)
        this.startActivity(intent)

      }
    cardview3.setOnClickListener {
      PreferenceManager.getDefaultSharedPreferences(this).edit().putInt("option", 3).apply()

      val intent = Intent(this, WeekActivity::class.java)
        this.startActivity(intent)

      }
      try {
        val json = JSONObject(result)
        val city = json.get("name")
        val json2 = json.getJSONObject("main")

        val wind = json.getJSONObject("wind")
        val speed = wind.get("speed")
        var deg="0"
        try {
           deg = wind.get("deg") as String
        }catch (j: JSONException){

        }catch (c: ClassCastException){

        }
        val weather = json.getJSONArray("weather")
        val main = weather.getJSONObject(0).get("main")
        val description = weather.getJSONObject(0).get("description")
        val temp = json2.get("temp")
        val pressure = json2.get("pressure")
        val humidity = json2.get("humidity")
        Log.d("asdf", json.toString())
        Log.d("asdf", json2.toString())
        Log.d("asdf", temp.toString())
        val kelvin = java.lang.Double.parseDouble(temp.toString())

        val celsius = kelvin - 273.15F
        val temprature = celsius.toString()
        val newtemp = temprature.split(".")
        tvcity.setText(city.toString())
        tvtemp.setText(newtemp[0] + "°C")
        tvhumidity.setText(humidity.toString() + "%")
        typressure.setText(pressure.toString())
        tvheading.setText(main.toString())
        tvheading2.setText(description.toString())
        tvspeed.setText(speed.toString())

        val d = deg.toString()
        val arr = d.split(".")

        val degrees = Integer.parseInt(arr[0])
        var ticker = ""
        if (degrees > 0 && degrees < 91) {
          ticker = "NE"
        } else if (degrees > 90 && degrees < 181) {
          ticker = "SE"

        } else if (degrees > 180 && degrees < 271) {
          ticker = "SW"

        } else if (degrees > 270) {
          ticker = "NW"

        }
        tvdeg.setText(ticker)
        if(main.toString().toLowerCase().contains("haze")){
          ivmain.setImageDrawable(resources.getDrawable(R.drawable.haze))
        }else if(main.toString().toLowerCase().contains("clear")){
          ivmain.setImageDrawable(resources.getDrawable(R.drawable.sunny))

        }else if(main.toString().toLowerCase().contains("cloud")){
          ivmain.setImageDrawable(resources.getDrawable(R.drawable.cloudy))

        }else if(main.toString().toLowerCase().contains("rain")){
          ivmain.setImageDrawable(resources.getDrawable(R.drawable.rain))

        }else if(main.toString().toLowerCase().contains("snow")){
          ivmain.setImageDrawable(resources.getDrawable(R.drawable.snowfall))

        }

      }catch (N:NullPointerException){
        N.printStackTrace()
      }

      //cardview 2


      val s2 = PreferenceManager.getDefaultSharedPreferences(this@CurrentActivity).getInt("sp2", 0)
      var ct2 = PreferenceManager.getDefaultSharedPreferences(this@CurrentActivity).getString("c2", "")
      val code2 = mcodes[s2]

      val b2 = BackgroundTask(this)
      try {
        ct2 = URLEncoder.encode(ct2, "UTF8")
      } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
      }
      var Url2 = "http://api.openweathermap.org/data/2.5/weather?q=" + ct2 + "," + code2 + "&appid=6181a2306e49fb2b2df664832ff2a3e1"

      val result2 = b2.execute(Url2).get()
      val tvtemp2: TextView = this.findViewById<TextView>(R.id.temp2) as TextView
      val tvhumidity2: TextView = this.findViewById<TextView>(R.id.humidity2) as TextView
      val typressure2: TextView = this.findViewById<TextView>(R.id.pressure2) as TextView
      val tvheading12: TextView = this.findViewById<TextView>(R.id.heading12) as TextView
      val tvheading22: TextView = this.findViewById<TextView>(R.id.heading22) as TextView
      val tvspeed2: TextView = this.findViewById<TextView>(R.id.windspeed2) as TextView
      val tvdeg2: TextView = this.findViewById<TextView>(R.id.direction2) as TextView
      val tvcity2: TextView = this.findViewById<TextView>(R.id.location2) as TextView
      val ivmain2: ImageView = this.findViewById<ImageView>(R.id.ivmain2) as ImageView
      try {
        val json = JSONObject(result2)
        val city = json.get("name")
        val json2 = json.getJSONObject("main")

        val wind = json.getJSONObject("wind")
        val speed = wind.get("speed")
        val deg = wind.get("deg")

        val weather = json.getJSONArray("weather")
        val main = weather.getJSONObject(0).get("main")
        val description = weather.getJSONObject(0).get("description")
        val temp = json2.get("temp")
        val pressure = json2.get("pressure")
        val humidity = json2.get("humidity")
        Log.d("asdf", json.toString())
        Log.d("asdf", json2.toString())
        Log.d("asdf", temp.toString())
        val kelvin = java.lang.Double.parseDouble(temp.toString())

        val celsius = kelvin - 273.15F
        val temprature = celsius.toString()
        val newtemp = temprature.split(".")
        tvcity2.setText(city.toString())
        tvtemp2.setText(newtemp[0] + "°C")
        tvhumidity2.setText(humidity.toString() + "%")
        typressure2.setText(pressure.toString())
        tvheading12.setText(main.toString())
        tvheading22.setText(description.toString())
        tvspeed2.setText(speed.toString())

        val d = deg.toString()
        val arr = d.split(".")

        val degrees = Integer.parseInt(arr[0])
        var ticker = ""
        if (degrees > 0 && degrees < 91) {
          ticker = "NE"
        } else if (degrees > 90 && degrees < 181) {
          ticker = "SE"

        } else if (degrees > 180 && degrees < 271) {
          ticker = "SW"

        } else if (degrees > 270) {
          ticker = "NW"

        }
        tvdeg2.setText(ticker)
        if(main.toString().toLowerCase().contains("haze")){
          ivmain2.setImageDrawable(resources.getDrawable(R.drawable.haze))
        }else if(main.toString().toLowerCase().contains("clear")){
          ivmain2.setImageDrawable(resources.getDrawable(R.drawable.sunny))

        }else if(main.toString().toLowerCase().contains("cloud")){
          ivmain2.setImageDrawable(resources.getDrawable(R.drawable.cloudy))

        }else if(main.toString().toLowerCase().contains("rain")){
          ivmain2.setImageDrawable(resources.getDrawable(R.drawable.rain))

        }else if(main.toString().toLowerCase().contains("snow")){
          ivmain2.setImageDrawable(resources.getDrawable(R.drawable.snowfall))

        }

      }catch (N:NullPointerException){
        N.printStackTrace()
      }
      //end if cardview 2



      //cardview 3


      val s3 = PreferenceManager.getDefaultSharedPreferences(this@CurrentActivity).getInt("sp3", 0)
      var ct3 = PreferenceManager.getDefaultSharedPreferences(this@CurrentActivity).getString("c3", "")
      val code3 = mcodes[s3]
      try {
        ct3 = URLEncoder.encode(ct3, "UTF8")
      } catch (e: UnsupportedEncodingException) {
        e.printStackTrace()
      }
      val b3 = BackgroundTask(this)
      var Url3 = "http://api.openweathermap.org/data/2.5/weather?q=" + ct3 + "," + code3 + "&appid=6181a2306e49fb2b2df664832ff2a3e1"

      val result3 = b3.execute(Url3).get()
      val tvtemp3: TextView = this.findViewById<TextView>(R.id.temp3) as TextView
      val tvhumidity3: TextView = this.findViewById<TextView>(R.id.humidity3) as TextView
      val typressure3: TextView = this.findViewById<TextView>(R.id.pressure3) as TextView
      val tvheading13: TextView = this.findViewById<TextView>(R.id.heading13) as TextView
      val tvheading23: TextView = this.findViewById<TextView>(R.id.heading23) as TextView
      val tvspeed3: TextView = this.findViewById<TextView>(R.id.windspeed3) as TextView
      val tvdeg3: TextView = this.findViewById<TextView>(R.id.direction3) as TextView
      val tvcity3: TextView = this.findViewById<TextView>(R.id.location3) as TextView
      val ivmain3: ImageView = this.findViewById<ImageView>(R.id.ivmain3) as ImageView
      try {
        val json = JSONObject(result3)
        val city = json.get("name")
        val json2 = json.getJSONObject("main")

        val wind = json.getJSONObject("wind")
        val speed = wind.get("speed")
        val deg = wind.get("deg")

        val weather = json.getJSONArray("weather")
        val main = weather.getJSONObject(0).get("main")
        val description = weather.getJSONObject(0).get("description")
        val temp = json2.get("temp")
        val pressure = json2.get("pressure")
        val humidity = json2.get("humidity")
        Log.d("asdf", json.toString())
        Log.d("asdf", json2.toString())
        Log.d("asdf", temp.toString())
        val kelvin = java.lang.Double.parseDouble(temp.toString())

        val celsius = kelvin - 273.15F
        val temprature = celsius.toString()
        val newtemp = temprature.split(".")
        tvcity3.setText(city.toString())
        tvtemp3.setText(newtemp[0] + "°C")
        tvhumidity3.setText(humidity.toString() + "%")
        typressure3.setText(pressure.toString())
        tvheading13.setText(main.toString())
        tvheading23.setText(description.toString())
        tvspeed3.setText(speed.toString())

        val d = deg.toString()
        val arr = d.split(".")

        val degrees = Integer.parseInt(arr[0])
        var ticker = ""
        if (degrees > 0 && degrees < 91) {
          ticker = "NE"
        } else if (degrees > 90 && degrees < 181) {
          ticker = "SE"

        } else if (degrees > 180 && degrees < 271) {
          ticker = "SW"

        } else if (degrees > 270) {
          ticker = "NW"

        }
        tvdeg3.setText(ticker)
        if(main.toString().toLowerCase().contains("haze")){
          ivmain3.setImageDrawable(resources.getDrawable(R.drawable.haze))
        }else if(main.toString().toLowerCase().contains("clear")){
          ivmain3.setImageDrawable(resources.getDrawable(R.drawable.sunny))

        }else if(main.toString().toLowerCase().contains("cloud")){
          ivmain3.setImageDrawable(resources.getDrawable(R.drawable.cloudy))

        }else if(main.toString().toLowerCase().contains("rain")){
          ivmain3.setImageDrawable(resources.getDrawable(R.drawable.rain))

        }else if(main.toString().toLowerCase().contains("snow")){
          ivmain3.setImageDrawable(resources.getDrawable(R.drawable.snowfall))

        }

      }catch (N:NullPointerException){
        N.printStackTrace()
      }
      //end if cardview 3
    }
}
