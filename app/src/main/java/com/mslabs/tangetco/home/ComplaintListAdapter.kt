package com.mslabs.tangetco.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.RecyclerView
import com.mslabs.tangetco.R
import com.mslabs.tangetco.api.response.ComplaintList
import kotlinx.android.synthetic.main.row_complaint_list.view.*

/**
 * Created by mslabs on 09/01/18.
 */
class ComplaintListAdapter(
    val context: Context,
    val complaintLists: MutableList<ComplaintList>,
    val listener: ComplaintListClickListener,
    val complaintTakeActionClickListener: ComplaintTakeActionClickListener,
    val complaintCompletedClickListener: ComplaintCompletedClickListener,
    val complaintTransferClickListener: ComplaintTransferClickListener, val complaintUpdateClickListener: ComplaintUpdateClickListener
) : RecyclerView.Adapter<ComplaintListAdapter.ComplaintViewHolder>() {
    var complaintfilteredList: MutableList<ComplaintList>? = null

    init {
        this.complaintfilteredList = ArrayList()
        (this.complaintfilteredList as ArrayList<ComplaintList>).addAll(complaintLists)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ComplaintViewHolder {
        val itemView =
            LayoutInflater.from(parent.context).inflate(R.layout.row_complaint_list, parent, false)
        return ComplaintViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ComplaintViewHolder, position: Int) {

        val complaintList = complaintLists[position]

        // holder.bind(complaintList,listener)

        holder.complaintid.text = complaintList.complaintNo.toString()

        holder.initiateWork.setOnClickListener {
            complaintTakeActionClickListener.onComplaintTakeActionClicked(complaintList)
        }
        holder.update.setOnClickListener {
            complaintUpdateClickListener.onComplaintUpdateClicked(complaintList)
        }

        holder.transfer.setOnClickListener {
            complaintTransferClickListener.onComplaintTransferClicked(complaintList)
        }

        holder.close.setOnClickListener {
            complaintCompletedClickListener.onComplaintCompletedClicked(complaintList)

        }
    }


    override fun getItemCount(): Int {
        return complaintLists.size
    }

    interface ComplaintListClickListener {
        fun onComplaintListClicked(complaintDataList: ComplaintList)
    }

    interface ComplaintTakeActionClickListener {
        fun onComplaintTakeActionClicked(complaintDataList: ComplaintList)
    }

    interface ComplaintCompletedClickListener {
        fun onComplaintCompletedClicked(complaintDataList: ComplaintList)
    }

    interface ComplaintTransferClickListener {
        fun onComplaintTransferClicked(complaintDataList: ComplaintList)
    }

    interface ComplaintUpdateClickListener {
        fun onComplaintUpdateClicked(complaintDataList: ComplaintList)
    }

    class ComplaintViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {


        var complaintid: TextView = itemView.complaintNumber
        var initiateWork: ConstraintLayout = itemView.initiateWork
        var update: ConstraintLayout = itemView.update
        var transfer: ConstraintLayout = itemView.transfer
        var close: ConstraintLayout = itemView.close


        fun bind(
            complaintList: ComplaintList,
            complaintListClickListener: ComplaintListClickListener
        ) {
            // binding.viewModel = complaintList

            itemView.setOnClickListener {

                complaintListClickListener.onComplaintListClicked(complaintList)
            }
        }
    }


}