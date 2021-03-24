package com.mslabs.tangetco.home.SectionForm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.mslabs.tangetco.R


class HeaderAdapter(subjects: ArrayList<SectionHeader>, context: Context,val onitemClickListener: ChildAdapter.onItemClickListener) :
    RecyclerView.Adapter<HeaderAdapter.ViewHolder>() {
    var subjects: ArrayList<SectionHeader> = subjects
    private val context: Context = context
    private val layoutInflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewHolder {
        val view: View = layoutInflater.inflate(R.layout.item_row_head, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: ViewHolder,
        position: Int
    ) {
        holder.recyclerView.adapter = ChildAdapter(context, position,subjects[position].headName,
            subjects[position].childList as ArrayList<Child>,onitemClickListener
        )
        holder.recyclerView.layoutManager = LinearLayoutManager(
            context,
            LinearLayoutManager.HORIZONTAL,
            false
        )
        holder.recyclerView.setHasFixedSize(true)
        holder.tvHeading.text = subjects[position].headName
    }

    override fun getItemCount(): Int {
        return subjects.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var recyclerView: RecyclerView = itemView.findViewById(R.id.recyclerView)
        var tvHeading: TextView = itemView.findViewById(R.id.tvItem)

    }

}
