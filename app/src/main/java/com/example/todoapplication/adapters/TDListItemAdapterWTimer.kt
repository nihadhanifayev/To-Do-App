package com.example.todoapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.classes.TDListItemWTimer
import com.example.todoapplication.R

class TDListItemAdapterWTimer(private val mContext: Context,private var TDListItems:List<TDListItemWTimer>):RecyclerView.Adapter<TDListItemAdapterWTimer.CardDesignObjectsListItemsWTimer>() {


    inner class CardDesignObjectsListItemsWTimer(design: View):RecyclerView.ViewHolder(design){
        var cardViewItem:CardView
        var chechBoxItem:CheckBox
        var cardViewDes:CardView
        var textViewDate:TextView
        var textViewDesc:TextView
        var cardViewDeleteImage:ImageView

        init {
            cardViewItem = design.findViewById(R.id.cardViewItemWithTimer)
            chechBoxItem = design.findViewById(R.id.checkBoxWithTimer)
            cardViewDes = design.findViewById(R.id.cardViewDescWithTimer)
            textViewDate = design.findViewById(R.id.textViewItemDate)
            textViewDesc = design.findViewById(R.id.textViewDescWithTimerCard)
            cardViewDeleteImage = design.findViewById(R.id.imageViewDeleteWithTimer)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignObjectsListItemsWTimer {
        val design = LayoutInflater.from(mContext).inflate(R.layout.card_design_list_item_with_timer,parent,false)
        return CardDesignObjectsListItemsWTimer(design)
    }

    override fun getItemCount(): Int {
        return TDListItems.size
    }

    override fun onBindViewHolder(holder: CardDesignObjectsListItemsWTimer, position: Int) {
        var ListItem = TDListItems.get(position)
    }
}