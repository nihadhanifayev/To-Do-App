package com.example.todoapplication.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.classes.TDListWOTImer
import com.example.todoapplication.R

class TDListAdapterWOTimer(private var mContext:Context,private var TDLIST:List<TDListWOTImer>):RecyclerView.Adapter<TDListAdapterWOTimer.CardDesignPbjectsWithoutTimerList>() {

    inner class CardDesignPbjectsWithoutTimerList(design:View):RecyclerView.ViewHolder(design){
        var cardViewWOtimer:CardView
        var textViewTitle:TextView
        var imageViewDetails:ImageView

        init {
            cardViewWOtimer = design.findViewById(R.id.cardViewWithoutTimer)
            textViewTitle = design.findViewById(R.id.textViewTitleWithoutTImer)
            imageViewDetails = design.findViewById(R.id.imageViewMenuWithoutTimer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignPbjectsWithoutTimerList {
        val design = LayoutInflater.from(mContext).inflate(R.layout.card_design_without_timer,parent,false)
        return CardDesignPbjectsWithoutTimerList(design)
    }

    override fun getItemCount(): Int {
        return TDLIST.size
    }

    override fun onBindViewHolder(holder: CardDesignPbjectsWithoutTimerList, position: Int) {
        var list = TDLIST.get(position)
    }

}