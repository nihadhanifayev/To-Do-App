package com.example.todoapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapplication.adapters.TDListAdapterWOTimer
import com.example.todoapplication.classes.TDListWOTImer
import com.example.todoapplication.databinding.ActivityTdlistWithoutTimerBinding
import com.example.todoapplication.models.TDListWithoutTimerModel

class TDListWithoutTimer : AppCompatActivity() {
    private lateinit var design:ActivityTdlistWithoutTimerBinding
    private val viewmodel:TDListWithoutTimerModel by viewModels()
    private lateinit var TDList:ArrayList<TDListWOTImer>
    private lateinit var adapter:TDListAdapterWOTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_tdlist_without_timer)
        design.tdListWithoutTimerObject = this
        viewmodel.tdListAllList()
        viewmodel.livedata.observe(this,{list ->
            design.list = list
            TDList = list
            adapter = TDListAdapterWOTimer(this,TDList,viewmodel)
            design.adapter = adapter
        })
    }

    fun fabClick(){
        val alertdialog = AlertDialog.Builder(this@TDListWithoutTimer)

        val designAlert = layoutInflater.inflate(R.layout.alert_design_without_timer,null)
        var title = designAlert.findViewById(R.id.editTextAlertTitleWithoutTimer) as EditText
        alertdialog.setTitle("To-Do List Create")
        alertdialog.setIcon(R.drawable.desc)
        alertdialog.setView(designAlert)

        alertdialog.setPositiveButton("Ok"){dialoginterface,i ->
            viewmodel.tdListAddList(title.text.toString())
        }
        alertdialog.setNegativeButton("Cancel"){dialoginterface,i ->

        }
        alertdialog.show()
    }
}