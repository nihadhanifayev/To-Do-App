package com.example.todoapplication.adapters

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.content.Context
import android.content.DialogInterface
import android.content.Intent
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.classes.TDListWTimer
import com.example.todoapplication.R
import com.example.todoapplication.TDListItems
import com.example.todoapplication.models.TDListTimerModel
import java.util.Calendar

class TDListAdapterWTimer(private var mContext:Context,private var TDLIST:List<TDListWTimer>,private var viewModel:TDListTimerModel):RecyclerView.Adapter<TDListAdapterWTimer.CardDesignObjectsWithTimerList>() {

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

        holder.textViewTitle.text = list.list_title
        holder.textViewDate.text = list.list_date
        holder.cardViewWithTimer.setOnClickListener {
            val intent = Intent(mContext,TDListItems::class.java)
            intent.putExtra("List",list)
            mContext.startActivity(intent)
        }
        holder.imageViewDetail.setOnClickListener {

            val popupMenu = PopupMenu(mContext,holder.imageViewDetail)
            popupMenu.menuInflater.inflate(R.menu.tdlist_menu_detail,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener ( PopupMenu.OnMenuItemClickListener { item ->
                when (item.itemId) {
                    R.id.action_delete -> {
                        viewModel.tdlistWithTimerRemoveList(list.list_no)
                        true
                    }
                    R.id.action_edit -> {
                        val alertDialogUpdate = AlertDialog.Builder(mContext)
                        val alertdialog = AlertDialog.Builder(mContext)
                        val designAlert = LayoutInflater.from(mContext).inflate(R.layout.alert_design_with_timer,null)
                        var title = designAlert.findViewById(R.id.editTextAlertTitleWithTimer) as EditText
                        var date = designAlert.findViewById(R.id.editTextDate) as EditText
                        alertdialog.setTitle("Edit List")
                        alertdialog.setIcon(R.drawable.desc)
                        alertdialog.setView(designAlert)

                        date.setOnClickListener {
                            val calendar = Calendar.getInstance()

                            val day = calendar.get(Calendar.DAY_OF_MONTH)
                            val month = calendar.get(Calendar.MONTH)
                            val year = calendar.get(Calendar.YEAR)

                            val datePicker = DatePickerDialog(mContext,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                                date.setText("$day/${month+1}/$year")
                            },year,month,day)
                            datePicker.setTitle("Date")
                            datePicker.setButton(DialogInterface.BUTTON_POSITIVE,"Ok",datePicker)
                            datePicker.setButton(DialogInterface.BUTTON_NEGATIVE,"Cancel",datePicker)
                            datePicker.show()
                        }
                        alertdialog.setPositiveButton("Ok"){dialoginterface,i ->
                            viewModel.tdlistTimerEdit(list.list_no,title.text.toString(),date.text.toString())
                        }
                        alertdialog.setNegativeButton("Cancel"){dialoginterface,i ->
                        }
                        alertdialog.show()
                        true
                    }
                    else -> false
                }

            })
            popupMenu.show()
        }
    }
}