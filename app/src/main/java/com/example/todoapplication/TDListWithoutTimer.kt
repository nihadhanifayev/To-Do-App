package com.example.todoapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapplication.databinding.ActivityTdlistWithoutTimerBinding

class TDListWithoutTimer : AppCompatActivity() {
    private lateinit var design:ActivityTdlistWithoutTimerBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_tdlist_without_timer)
        design.tdListWithoutTimerObject = this
    }

    fun fabClick(){
        val alertdialog = AlertDialog.Builder(this@TDListWithoutTimer)

        val designAlert = layoutInflater.inflate(R.layout.alert_design_without_timer,null)
        alertdialog.setTitle("To-Do List Create")
        alertdialog.setIcon(R.drawable.desc)
        alertdialog.setView(designAlert)

        alertdialog.setPositiveButton("Ok"){dialoginterface,i ->
            startActivity(Intent(this@TDListWithoutTimer, TDListItemsWithoutTimer::class.java))
        }
        alertdialog.setNegativeButton("Cancel"){dialoginterface,i ->

        }
        alertdialog.show()
    }
}