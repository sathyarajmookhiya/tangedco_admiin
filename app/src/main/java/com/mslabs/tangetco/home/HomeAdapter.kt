package com.mslabs.tangetco.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.intrusoft.sectionedrecyclerview.SectionRecyclerViewAdapter
import com.mslabs.tangetco.R
import com.mslabs.tangetco.home.SectionForm.Child
import com.mslabs.tangetco.home.SectionForm.SectionHeader
import kotlinx.android.synthetic.main.item_child.view.*
import kotlinx.android.synthetic.main.item_row_head.view.*


class HomeAdapter(
    var context: Context,
    sectionItemList: List<SectionHeader>
) :
    SectionRecyclerViewAdapter<SectionHeader, Child, SectionViewHolder, ChildViewHolder>(
        context,
        sectionItemList
    ) {


    var color: Int = 0

    override fun onCreateSectionViewHolder(
        sectionViewGroup: ViewGroup,
        viewType: Int
    ): SectionViewHolder {
        val view = LayoutInflater.from(context)
            .inflate(R.layout.item_row_head, sectionViewGroup, false)
        return SectionViewHolder(view)
    }

    override fun onCreateChildViewHolder(
        childViewGroup: ViewGroup,
        viewType: Int
    ): ChildViewHolder {
        val view =
            LayoutInflater.from(context).inflate(R.layout.item_child, childViewGroup, false)
        return ChildViewHolder(view)
    }

    override fun onBindSectionViewHolder(
        sectionViewHolder: SectionViewHolder,
        sectionPosition: Int,
        section: SectionHeader
    ) {
        sectionViewHolder.headerText.text = section.headName


    }


    override fun onBindChildViewHolder(
        childViewHolder: ChildViewHolder,
        sectionPosition: Int,
        childPosition: Int,
        child: Child
    ) {
        when (childPosition) {
            0 -> {
                childViewHolder.countofCmpl.text = child.Pending
                childViewHolder.title.text = "Pending"
                childViewHolder.cardBack.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.circle_greay
                    )
                )
                childViewHolder.imageView.setImageResource(R.drawable.submit_icon)

            }
            1 -> {
                childViewHolder.countofCmpl.text = child.InProgress
                childViewHolder.title.text = "InProgress"
                childViewHolder.cardBack.setCardBackgroundColor(
                    ContextCompat.getColor(
                        context,
                        R.color.submit_color
                    )
                )
                childViewHolder.imageView.setImageResource(R.drawable.process_icon)

            }

        }

    }

}

class ChildViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    var countofCmpl: TextView = itemView.textViewCount
    var title: TextView = itemView.title
    var cardBack: CardView = itemView.cardBack
    var imageView: ImageView = itemView.image_view

}

class SectionViewHolder(headerView: View) : RecyclerView.ViewHolder(headerView) {

    var headerText: TextView = headerView.tvItem
}
