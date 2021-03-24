package com.mslabs.tangetco.home.SectionForm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.mslabs.tangetco.R
import kotlinx.android.synthetic.main.item_child.view.*


class ChildAdapter(context: Context,val headerPosition: Int,val headerName: String, childs: ArrayList<Child>,val onitemClickListener: onItemClickListener) :
    RecyclerView.Adapter<ChildAdapter.CustomViewHolder>() {
    private val context: Context = context
    private val childs: ArrayList<Child> = childs
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CustomViewHolder {
        val view: View = inflater.inflate(R.layout.item_child, parent, false)
        return CustomViewHolder(view)
    }

    override fun onBindViewHolder(
        holder: CustomViewHolder,
        position: Int
    ) {
        val child: Child = childs[position]

        when (position) {
            0-> {
                holder.cardBack.visibility=View.VISIBLE
                holder.textViewCount.text = child.Pending
                holder.title.text = "Pending"
                holder.cardBack.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.circle_greay
                    )
                )
                holder.imageView.setImageResource(R.drawable.submit_icon)
            }
            1-> {
                holder.cardBack.visibility=View.VISIBLE
                holder.textViewCount.text = child.InProgress
                holder.title.text = "InProgress"
                holder.imageView.setImageResource(R.drawable.process_icon)
            }
            else ->
            {
                holder.cardBack.visibility=View.GONE
            }

        }
        holder.itemView.setOnClickListener {
            if(position==0)
            {
                onitemClickListener.onItemClick(headerPosition,headerName,"Pending",child)
            }
            else
            {
                onitemClickListener.onItemClick(headerPosition,headerName,"InProgress",child)

            }
        }
    }

    override fun getItemCount(): Int {
        return childs.size
    }

    inner class CustomViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var textViewCount: TextView = itemView.textViewCount
        var title: TextView = itemView.title
        var cardBack: CardView = itemView.cardBack
        var imageView: ImageView = itemView.image_view
    }

    interface onItemClickListener
    {
        fun onItemClick(headerId: Int,headerName: String,childName: String,child: Child)
    }
}
