package com.example.todoapplication.Adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.Classes.TDListWTimer
import com.example.todoapplication.R

class TDListAdapterWTimer(private var mContext:Context,private var TDLIST:List<TDListWTimer>):RecyclerView.Adapter<TDListAdapterWTimer.CardDesignObjectsWithTimerList>() {

    inner class CardDesignObjectsWithTimerList(design:View):RecyclerView.ViewHolder(design){
        var cardViewWithTimer:CardView
        var textViewTitle:TextView
        var textViewDate:TextView
        var imageViewDetail:ImageView

        init {
            cardViewWithTimer = design.findViewById(R.id.cardViewListWithTimer)
            textViewTitle = design.findViewById(R.id.textViewTitle)
            textViewDate = design.findViewById(R.id.textViewDate)
            imageViewDetail = design.findViewById(R.id.imageViewMenuWithTimer)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignObjectsWithTimerList {
        val tasarim = LayoutInflater.from(mContext).inflate(R.layout.tdlist_with_timer_card_design,parent,false)
        return CardDesignObjectsWithTimerList(tasarim)
    }

    override fun getItemCount(): Int {
        return TDLIST.size
    }

    override fun onBindViewHolder(holder: CardDesignObjectsWithTimerList, position: Int) {
        var list = TDLIST.get(position)
    }
}