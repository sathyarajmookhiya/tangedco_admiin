package com.mslabs.tangetco.home.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.mslabs.tangetco.R
import com.mslabs.tangetco.api.response.ListDataResponse

open class SubDivisionAdapter(data: List<ListDataResponse>, val context: Context) : BaseAdapter() {

    val mInflater: LayoutInflater
    var localData = data

    init {
        mInflater = LayoutInflater.from(context)

    }

    override fun getCount(): Int {
        return localData.size
    }

    override fun getItem(position: Int): Any {
        return localData[position]
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View? {


        val view: View?
        val vh: ListRowHolder
        if (convertView == null) {
            view = mInflater.inflate(R.layout.list_item, parent, false)
            vh = ListRowHolder(view)
            view?.tag = vh
        } else {
            view = convertView
            vh = view.tag as ListRowHolder
        }
        vh.label.text = localData.get(position).name
        return view
    }

    private class ListRowHolder(row: View?) {

        val label: TextView

        init {
            this.label = row?.findViewById(R.id.namelist) as TextView
        }
    }

}

