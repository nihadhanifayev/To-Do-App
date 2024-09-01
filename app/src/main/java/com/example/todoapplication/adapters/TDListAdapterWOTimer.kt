package com.example.todoapplication.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.classes.TDListWOTImer
import com.example.todoapplication.R
import com.example.todoapplication.TDListItemsWithoutTimer
import com.example.todoapplication.models.TDListWithoutTimerModel

class TDListAdapterWOTimer(private var mContext:Context,private var TDLIST:List<TDListWOTImer>,private var viewmodel:TDListWithoutTimerModel):RecyclerView.Adapter<TDListAdapterWOTimer.CardDesignPbjectsWithoutTimerList>() {

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

        holder.textViewTitle.text = list.list_title
        holder.cardViewWOtimer.setOnClickListener {
            val intent = Intent(mContext,TDListItemsWithoutTimer::class.java)
            intent.putExtra("List",list)
            mContext.startActivity(intent)
        }
        holder.imageViewDetails.setOnClickListener {
            val popupMenu = PopupMenu(mContext,holder.imageViewDetails)
            popupMenu.menuInflater.inflate(R.menu.tdlist_menu_detail,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item->
                when(item.itemId){
                    R.id.action_delete -> {
                        viewmodel.deleteList(list.list_no)
                        true
                    }
                    R.id.action_edit -> {
                        val alertdialog = AlertDialog.Builder(mContext)
                        val designAlert = LayoutInflater.from(mContext).inflate(R.layout.alert_design_without_timer,null)
                        var title = designAlert.findViewById(R.id.editTextAlertTitleWithoutTimer) as EditText
                        alertdialog.setTitle("Edit List")
                        alertdialog.setIcon(R.drawable.desc)
                        alertdialog.setView(designAlert)

                        alertdialog.setPositiveButton("Ok"){dialoginterface,i ->
                            viewmodel.tdlistWithoutTimerEdit(list.list_no,title.text.toString())
                        }
                        alertdialog.setNegativeButton("Cancel"){dialoginterface,i ->
                        }
                        alertdialog.show()
                        true
                    }
                    else -> {
                        false
                    }
                }
            })
            popupMenu.show()
        }
    }

}