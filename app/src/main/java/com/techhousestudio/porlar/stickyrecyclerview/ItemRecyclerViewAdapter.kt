package com.techhousestudio.porlar.stickyrecyclerview

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.extensions.LayoutContainer
import kotlinx.android.synthetic.main.recycler_item_header_row.view.*
import kotlinx.android.synthetic.main.recycler_item_row.view.*
import java.text.SimpleDateFormat
import java.util.*

class ItemRecyclerViewAdapter :
    RecyclerView.Adapter<ItemRecyclerViewAdapter.MyItemViewHolder>() {
     var itemList: List<Item> = emptyList()
    set(value) {
        this.notifyDataSetChanged()
        field = value
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ItemRecyclerViewAdapter.MyItemViewHolder =
        when (viewType) {
            HEADER_TYPE ->
                MyItemViewHolder(
                    LayoutInflater.from(parent.context).inflate(
                        R.layout.recycler_item_header_row,
                        parent,
                        false
                    )
                )
            BODY_TYPE -> MyItemViewHolder(
                LayoutInflater.from(parent.context).inflate(
                    R.layout.recycler_item_row,
                    parent,
                    false
                )
            )


            else -> MyItemViewHolder(parent) // error
        }


    class MyItemViewHolder(override val containerView: View) :
        RecyclerView.ViewHolder(containerView), LayoutContainer {
        fun headerBind(item: Item.HeaderItem) {
            containerView.tvDate.text = SimpleDateFormat("EEE, MMM d, ''yy", Locale.ENGLISH).format(item.date)
        }
        fun bodyBind(item: Item.BodyItem){
            containerView.tvContent.text = item.content
        }

    }

    override fun getItemViewType(position: Int): Int {
        return when (itemList[position]) {
            is Item.HeaderItem -> HEADER_TYPE
            is Item.BodyItem -> BODY_TYPE
        }
    }

    override fun getItemCount(): Int = itemList.size
    override fun onBindViewHolder(holder: ItemRecyclerViewAdapter.MyItemViewHolder, position: Int) {
        when (itemList[position]) {
            is Item.HeaderItem -> {

                holder.headerBind(itemList[position] as Item.HeaderItem)
            }
            is Item.BodyItem -> {
                holder.bodyBind(itemList[position] as Item.BodyItem)
            }
        }
    }

    companion object {
        private const val HEADER_TYPE = 1
        private const val BODY_TYPE = 2;
    }
}