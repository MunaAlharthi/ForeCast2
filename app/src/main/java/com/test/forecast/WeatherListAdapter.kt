package com.test.forecast

import android.app.Activity
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class WeatherListAdapter(private var activity: Activity, private var items: ArrayList<DataModel>): BaseAdapter() {

    private class ViewHolder(row: View?) {
        var datetime: TextView? = null
        var title: TextView? = null
        var tv_temp: TextView? = null
        var iv_lv: ImageView? = null

        init {
            this.datetime = row?.findViewById<TextView>(R.id.datetime)
            this.title = row?.findViewById<TextView>(R.id.title)
            this.tv_temp = row?.findViewById<TextView>(R.id.tv_temp)
            this.iv_lv = row?.findViewById<ImageView>(R.id.iv_lv)
        }
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        val view: View?
        val viewHolder: ViewHolder
        if (convertView == null) {
            val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            view = inflater.inflate(R.layout.listitem_layout, null)
            viewHolder = ViewHolder(view)
            view?.tag = viewHolder
        } else {
            view = convertView
            viewHolder = view.tag as ViewHolder
        }

        var dataModel = items[position]
        viewHolder.datetime?.text = dataModel.date
        viewHolder.title?.text = dataModel.titile
        viewHolder.tv_temp?.text = dataModel.temp
        if( viewHolder.title.toString().toLowerCase().contains("haze")){
            viewHolder.iv_lv?.setImageDrawable(activity.resources.getDrawable(R.drawable.haze))
        }else if( viewHolder.title.toString().toLowerCase().contains("clear")){
            viewHolder.iv_lv?.setImageDrawable(activity.resources.getDrawable(R.drawable.sunny))

        }else if( viewHolder.title.toString().toLowerCase().contains("cloud")){
            viewHolder.iv_lv?.setImageDrawable(activity.resources.getDrawable(R.drawable.cloudy))

        }else if( viewHolder.title.toString().toLowerCase().contains("rain")){
            viewHolder.iv_lv?.setImageDrawable(activity.resources.getDrawable(R.drawable.rain))

        }else if( viewHolder.title.toString().toLowerCase().contains("snow")){
            viewHolder.iv_lv?.setImageDrawable(activity.resources.getDrawable(R.drawable.snowfall))

        }

        //  viewHolder.iv_lv?.setImageDrawable(getView()) = dataModel.pic

        return view as View
    }

    override fun getItem(i: Int): DataModel {
        return items[i]
    }

    override fun getItemId(i: Int): Long {
        return i.toLong()
    }

    override fun getCount(): Int {
        return items.size
    }
}
