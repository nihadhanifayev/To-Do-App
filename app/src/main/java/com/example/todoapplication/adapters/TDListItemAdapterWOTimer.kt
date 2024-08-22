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
import com.example.todoapplication.classes.TDListItemWOTimer
import com.example.todoapplication.R

class TDListItemAdapterWOTimer(private var mContext:Context,private var TDListItem:List<TDListItemWOTimer>):RecyclerView.Adapter<TDListItemAdapterWOTimer.CardDesignObjectsListItemWoTimer>() {

    inner class CardDesignObjectsListItemWoTimer(design:View):RecyclerView.ViewHolder(design){

        var cardViewItem: CardView
        var chechBoxItem: CheckBox
        var cardViewDes: CardView
        var textViewDesc: TextView
        var cardViewDeleteImage: ImageView

        init {
            cardViewItem = design.findViewById(R.id.cardViewItemWithoutTimer)
            chechBoxItem = design.findViewById(R.id.checkBoxWithoutTimer)
            cardViewDes = design.findViewById(R.id.cardViewDescWithoutTimer)
            textViewDesc = design.findViewById(R.id.textViewDescWithoutTimer)
            cardViewDeleteImage = design.findViewById(R.id.imageViewDeleteWithoutTimer)

        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignObjectsListItemWoTimer {
        var design = LayoutInflater.from(mContext).inflate(R.layout.card_design_list_item_without_timer,parent,false)
        return CardDesignObjectsListItemWoTimer(design)
    }

    override fun getItemCount(): Int {
        return TDListItem.size
    }

    override fun onBindViewHolder(holder: CardDesignObjectsListItemWoTimer, position: Int) {
        var item = TDListItem.get(position)
    }
}