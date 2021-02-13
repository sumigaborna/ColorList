package com.sumigaborna.colorlist.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sumigaborna.colorlist.R
import com.sumigaborna.colorlist.database.ColorItem
import com.sumigaborna.colorlist.ui.fragments.ColorItemListener

class ColorsAdapter(private val colorItemListener: ColorItemListener) :
    PagingDataAdapter<ColorItem, ColorsAdapter.ColorItemViewHolder>(diffCallback) {
    override fun onBindViewHolder(holder: ColorItemViewHolder, position: Int) {
        holder.bindTo(getItem(position))
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ColorItemViewHolder =
        ColorItemViewHolder(parent)

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<ColorItem>() {
            override fun areItemsTheSame(oldItem: ColorItem, newItem: ColorItem): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: ColorItem, newItem: ColorItem): Boolean =
                oldItem == newItem
        }
    }

    inner class ColorItemViewHolder(parent: ViewGroup) : RecyclerView.ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_color, parent, false)
    ) {

        private val vColor = itemView.findViewById<View>(R.id.vColor)
        private val tvNumber = itemView.findViewById<TextView>(R.id.tvNumber)

        fun bindTo(colorItem: ColorItem?) {
            colorItem?.let {
                vColor.setBackgroundColor(it.color)
                vColor.setOnClickListener { _ ->
                    colorItemListener.openColorItem(it.id)
                }
                tvNumber.text = it.id.toString()
            }
        }
    }
}