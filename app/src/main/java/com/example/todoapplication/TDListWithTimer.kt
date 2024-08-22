package com.example.todoapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapplication.adapters.TDListAdapterWTimer
import com.example.todoapplication.classes.TDListWTimer
import com.example.todoapplication.databinding.ActivityTdlistWithTimerBinding

class TDListWithTimer : AppCompatActivity() {
    private lateinit var design:ActivityTdlistWithTimerBinding
    private lateinit var TDList:ArrayList<TDListWTimer>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_tdlist_with_timer)
        design.tdlistWithTimerObject = this

    }

    fun fabClick(){
        val alertdialog = AlertDialog.Builder(this@TDListWithTimer)

        val designAlert = layoutInflater.inflate(R.layout.alert_design_with_timer,null)
        alertdialog.setTitle("To-Do List Create")
        alertdialog.setIcon(R.drawable.desc)
        alertdialog.setView(designAlert)

        alertdialog.setPositiveButton("Ok"){dialoginterface,i ->
            startActivity(Intent(this@TDListWithTimer, TDListItems::class.java))
        }
        alertdialog.setNegativeButton("Cancel"){dialoginterface,i ->

        }
        alertdialog.show()
    }
}