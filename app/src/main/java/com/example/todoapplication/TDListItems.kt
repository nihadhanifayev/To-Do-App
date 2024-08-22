package com.example.todoapplication

import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapplication.databinding.ActivityTdlistItemsBinding

class TDListItems : AppCompatActivity() {
    private lateinit var design:ActivityTdlistItemsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this, R.layout.activity_tdlist_items)
        design.tdListItemWithTimer = this

    }
    fun fabClick(){
        val alertdialog = AlertDialog.Builder(this@TDListItems)

        val designAlert = layoutInflater.inflate(R.layout.alert_design_list_item_with_timer,null)
        alertdialog.setTitle("To-Do List Item Create")
        alertdialog.setIcon(R.drawable.desc)
        alertdialog.setView(designAlert)

        alertdialog.setPositiveButton("Ok"){dialoginterface,i ->

        }
        alertdialog.setNegativeButton("Cancel"){dialoginterface,i ->

        }
        alertdialog.show()
    }
}