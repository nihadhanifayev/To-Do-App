package com.example.todoapplication

import android.app.DatePickerDialog
import android.content.DialogInterface
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapplication.adapters.TDListAdapterWTimer
import com.example.todoapplication.classes.TDListWTimer
import com.example.todoapplication.databinding.ActivityTdlistWithTimerBinding
import com.example.todoapplication.models.TDListTimerModel
import java.util.Calendar

class TDListWithTimer : AppCompatActivity() {
    private lateinit var design:ActivityTdlistWithTimerBinding
    private lateinit var TDLIST:ArrayList<TDListWTimer>
    private lateinit var adapter:TDListAdapterWTimer
    private val viewmodel:TDListTimerModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_tdlist_with_timer)
        design.tdlistWithTimerObject = this
        viewmodel.tdlistWithTimerAllLists()
        viewmodel.livedata.observe(this,{list ->
            design.tdList = list
            TDLIST = list
            adapter = TDListAdapterWTimer(this,TDLIST,viewmodel)
            design.adapter = adapter
        })
    }



    fun fabClick(){
        val alertdialog = AlertDialog.Builder(this@TDListWithTimer)
        val designAlert = layoutInflater.inflate(R.layout.alert_design_with_timer,null)
        var title = designAlert.findViewById(R.id.editTextAlertTitleWithTimer) as EditText
        var date = designAlert.findViewById(R.id.editTextDate) as EditText
        alertdialog.setTitle("To-Do List Create")
        alertdialog.setIcon(R.drawable.desc)
        alertdialog.setView(designAlert)

        date.setOnClickListener {
            val calendar = Calendar.getInstance()

            val day = calendar.get(Calendar.DAY_OF_MONTH)
            val month = calendar.get(Calendar.MONTH)
            val year = calendar.get(Calendar.YEAR)

            val datePicker = DatePickerDialog(this@TDListWithTimer,DatePickerDialog.OnDateSetListener { view, year, month, dayOfMonth ->
                date.setText("$day/${month+1}/$year")
            },year,month,day)
            datePicker.setTitle("Date")
            datePicker.setButton(DialogInterface.BUTTON_POSITIVE,"Ok",datePicker)
            datePicker.setButton(DialogInterface.BUTTON_NEGATIVE,"Cancel",datePicker)
            datePicker.show()
        }
        alertdialog.setPositiveButton("Ok"){dialoginterface,i ->
            viewmodel.tdlistWithTimerAdd(title.text.toString(),date.text.toString())
        }
        alertdialog.setNegativeButton("Cancel"){dialoginterface,i ->
        }
        alertdialog.show()
    }
}