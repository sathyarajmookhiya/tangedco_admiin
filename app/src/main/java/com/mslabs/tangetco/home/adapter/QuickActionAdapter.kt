package com.mslabs.tangetco.home.adapter

import android.content.Context
import android.os.Build

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mslabs.tangetco.R
import com.mslabs.tangetco.api.response.DashboardList
import kotlinx.android.synthetic.main.row_item_dashboard.view.*

/**
 * Created by admin on 28-02-2018.
 */
class QuickActionAdapter(private val context: Context, private val quickActionList: MutableList<DashboardList>, private val listener: QuickActionClickListener) : RecyclerView.Adapter<QuickActionAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.row_item_dashboard, parent, false)
        return ViewHolder(itemView)
    }
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val quickAction = quickActionList[position]

        holder.complaintCount.text = quickAction.TotalCount.toString()
        holder.complaintName.text = quickAction.Complaintname
        holder.pendingCount.text = quickAction.Pending.toString()
        holder.inprogressCount.text = quickAction.InProgress.toString()

        holder.pendingLayout.setOnClickListener {
            listener.onQuickActionClicked(quickAction,position,0)
        }
        holder.InProgressLayout.setOnClickListener {
            listener.onQuickActionClicked(quickAction,position,1)
        }
        when (position) {
            0 -> {
                holder.imageView.setImageResource(R.drawable.ic_power_failure)

            }
            1 -> {
                holder.imageView.setImageResource(R.drawable.ic_fire_icon)

            }
            2 -> {
                holder.imageView.setImageResource(R.drawable.ic_power_failure)

            }
            3 -> {
                holder.imageView.setImageResource(R.drawable.ic_billing_icon)

            }
            4 -> {
                holder.imageView.setImageResource(R.drawable.ic_theft_icon)

            }
            5 -> {
                holder.imageView.setImageResource(R.drawable.ic_other_icon)

            }
            6 -> {
                holder.imageView.setImageResource(R.drawable.ic_theft_icon)

            }
            7 -> {
                holder.imageView.setImageResource(R.drawable.ic_fire_icon)

            }
            /*
             holder.imageView.setImageResource(quickAction.imageName)
             if (quickAction.backColor == 1) {
                 holder.backcolor.setBackgroundColor(ContextCompat.getColor(context, R.color.submit_color));

             } else if (quickAction.backColor == 2) {
                 holder.backcolor.setBackgroundColor(ContextCompat.getColor(context, R.color.processing_color));


             } else if (quickAction.backColor == 3) {

                 holder.backcolor.setBackgroundColor(ContextCompat.getColor(context, R.color.transfer_color));


             } else if (quickAction.backColor == 4) {
                 holder.backcolor.setBackgroundColor(ContextCompat.getColor(context, R.color.complited_color));


             } else {
                 holder.backcolor.setBackgroundColor(ContextCompat.getColor(context, R.color.submit_color));


             }*/
        }
       /*
        holder.imageView.setImageResource(quickAction.imageName)
        if (quickAction.backColor == 1) {
            holder.backcolor.setBackgroundColor(ContextCompat.getColor(context, R.color.submit_color));

        } else if (quickAction.backColor == 2) {
            holder.backcolor.setBackgroundColor(ContextCompat.getColor(context, R.color.processing_color));


        } else if (quickAction.backColor == 3) {

            holder.backcolor.setBackgroundColor(ContextCompat.getColor(context, R.color.transfer_color));


        } else if (quickAction.backColor == 4) {
            holder.backcolor.setBackgroundColor(ContextCompat.getColor(context, R.color.complited_color));


        } else {
            holder.backcolor.setBackgroundColor(ContextCompat.getColor(context, R.color.submit_color));


        }*/
    }

    override fun getItemCount(): Int {
        return quickActionList.size
    }

    interface QuickActionClickListener {
        fun onQuickActionClicked(quickAction: DashboardList,position:Int,status:Int)
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var complaintCount: TextView = itemView.totalCount
        var complaintName: TextView = itemView.titleName
        var pendingCount: TextView = itemView.pendingCount
        var inprogressCount: TextView = itemView.inprogressCount
        var imageView: ImageView = itemView.image_view
         var backcolor: LinearLayout = itemView.countLayout
         var InProgressLayout: LinearLayout = itemView.InProgressLayout
         var pendingLayout: LinearLayout = itemView.pendingLayout

       /* fun bind(quickAction: DashboardList,position: Int,status: Int, listener: QuickActionClickListener?) {
            itemView.setOnClickListener {
                listener?.onQuickActionClicked(quickAction,position,status)
            }
        }*/
    }
}
