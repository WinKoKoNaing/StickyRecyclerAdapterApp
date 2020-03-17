package com.techhousestudio.porlar.stickyrecyclerview

import HeaderItemDecoration
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {

    lateinit var itemRecyclerViewAdapter: ItemRecyclerViewAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val itemList = mutableListOf<Item>()

        for (index in 1..50) {
            var c = Calendar.getInstance()
            c.add(Calendar.YEAR, index)
            c.add(Calendar.MONTH, index)
            c.add(Calendar.DATE, index)//same with c.add(Calendar.DAY_OF_MONTH, 1);
            c.add(Calendar.HOUR, index)
            c.add(Calendar.MINUTE, index)
            c.add(Calendar.SECOND, index)
            itemList.add(Item.HeaderItem(c.time))
            itemList.add(Item.BodyItem("Welcome to My Application from $index"))
            itemList.add(Item.BodyItem("Welcome to My Application from $index"))
            itemList.add(Item.BodyItem("Welcome to My Application from $index"))

        }



        recycler_item_list.layoutManager = LinearLayoutManager(this)
        itemRecyclerViewAdapter = ItemRecyclerViewAdapter()
        recycler_item_list.adapter = itemRecyclerViewAdapter
        itemRecyclerViewAdapter.itemList = itemList


        recycler_item_list.addItemDecoration(HeaderItemDecoration(recycler_item_list) { itemPosition ->
            if (itemPosition >= 0 && itemPosition < itemRecyclerViewAdapter.itemCount) {
                // your code to check if item at itemPosition is header
                (recycler_item_list.adapter as ItemRecyclerViewAdapter).getItemViewType(itemPosition)== 1
            } else false
        })


    }


}
