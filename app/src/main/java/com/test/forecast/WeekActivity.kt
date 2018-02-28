package com.test.forecast

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.preference.PreferenceManager
import android.util.Log
import android.widget.Button
import android.widget.ListView
import org.json.JSONObject
import java.io.UnsupportedEncodingException
import java.net.URLEncoder

class WeekActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_week)

        val option = PreferenceManager.getDefaultSharedPreferences(this@WeekActivity).getInt("option", 1)
           val s1 = PreferenceManager.getDefaultSharedPreferences(this@WeekActivity).getInt("sp"+option, 0)
           var ct1 = PreferenceManager.getDefaultSharedPreferences(this@WeekActivity).getString("c"+option, "")

        try {
            ct1 = URLEncoder.encode(ct1, "UTF8")
        } catch (e: UnsupportedEncodingException) {
            e.printStackTrace()
        }
        val mcodes = getResources().getStringArray(R.array.array_codes)
        val code = mcodes[s1]

        var   adapter = WeatherListAdapter(this, generateData(ct1,code))
        val listView: ListView = this.findViewById<ListView>(R.id.lv) as ListView
        listView?.adapter = adapter
        adapter?.notifyDataSetChanged()



    }
    fun generateData( ct1:String,code:String): ArrayList<DataModel> {
        var resultx = ArrayList<DataModel>()
/*

        for (i in 0..9) {
            var data: DataModel = DataModel("Bett", "Awesome work ;)","fsdfa","fdf")
            result.add(data)
        }
*/

        val b = BackgroundTask(this)

        var Url = "http://api.openweathermap.org/data/2.5/forecast?q=" + ct1 + "," + code + "&appid=6181a2306e49fb2b2df664832ff2a3e1"

        val result = b.execute(Url).get()
        Log.d("chcc", result.toString())
        val jsonx = JSONObject(result)
        val Total_items = jsonx.get("cnt")
        val list = jsonx.getJSONArray("list")
        Log.d("chcc", list.toString())

        for (i in 0 until list.length()) {

            val json2 = list.getJSONObject(i)
            val json = json2.getJSONObject("main")
            val wind = json2.getJSONObject("wind")
            val speed = wind.get("speed")
            val deg = wind.get("deg")

            val weather = json2.getJSONArray("weather")
            val main = weather.getJSONObject(0).get("main")
            val dated = json2.get("dt_txt")
            val description = weather.getJSONObject(0).get("description")
            val temp = json.get("temp")
            val pressure = json.get("pressure")
            val humidity = json.get("humidity")
            Log.d("asdf main", main.toString())//label for title
            Log.d("asdf humidity", humidity.toString())
            Log.d("asdf pressure", pressure.toString())
            val kelvin = java.lang.Double.parseDouble(temp.toString())

            val celsius = kelvin - 273.15F
            val temprature = celsius.toString()
            val newtemp = temprature.split(".")
            Log.d("asdf temperature", newtemp[0].toString())
            var data: DataModel = DataModel(dated.toString(), main.toString(),newtemp[0].toString()+"°C","")
            resultx.add(data)

        }
            return resultx

    }
}
