package com.example.todoapplication

import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.DialogInterface
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapplication.adapters.TDListItemAdapterWTimer
import com.example.todoapplication.classes.TDListItemWTimer
import com.example.todoapplication.classes.TDListWTimer
import com.example.todoapplication.databinding.ActivityTdlistItemsBinding
import com.example.todoapplication.models.TDListItemsTimerModel
import java.util.Calendar

class TDListItems : AppCompatActivity() {
    private lateinit var design:ActivityTdlistItemsBinding
    private  val viewModel:TDListItemsTimerModel by viewModels()
    private lateinit var TDLIST:ArrayList<TDListItemWTimer>
    private lateinit var adapter:TDListItemAdapterWTimer
    private lateinit var list:TDListWTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this, R.layout.activity_tdlist_items)
        list = intent.getSerializableExtra("List") as TDListWTimer
        viewModel.TDListItemAllItems(list.list_no)
        design.tdListItemWithTimer = this
        design.listTitle = list.list_title
        viewModel.livedata.observe(this,{list->
            design.list = list
            TDLIST = list
            adapter = TDListItemAdapterWTimer(this,TDLIST,viewModel)
            design.adapter = adapter
        })



    }
    fun fabClick(){
        val alertdialog = AlertDialog.Builder(this@TDListItems)

        val designAlert = layoutInflater.inflate(R.layout.alert_design_list_item_with_timer,null)
        var title = designAlert.findViewById(R.id.editTextAlertItemTitleWithTimer) as EditText
        var time = designAlert.findViewById(R.id.editTextAlertTime) as EditText
        var desc = designAlert.findViewById(R.id.editTextAlertDescWithTimer) as EditText
        alertdialog.setTitle("To-Do List Item Create")
        alertdialog.setIcon(R.drawable.desc)
        alertdialog.setView(designAlert)
        time.setOnClickListener {
            val calendar = Calendar.getInstance()

            val hour = calendar.get(Calendar.HOUR_OF_DAY)
            val minute = calendar.get(Calendar.MINUTE)

            val timePicker = TimePickerDialog(this@TDListItems,
                TimePickerDialog.OnTimeSetListener { view, hour, minute ->
                    time.setText("$hour:$minute")
                },hour,minute,true)
            timePicker.setTitle("Time")
            timePicker.setButton(DialogInterface.BUTTON_POSITIVE,"Ok",timePicker)
            timePicker.setButton(DialogInterface.BUTTON_NEGATIVE,"Cancel",timePicker)
            timePicker.show()
        }
        alertdialog.setPositiveButton("Ok"){dialoginterface,i ->
            viewModel.TDListItemAddTimer(list.list_no,title.text.toString(),desc.text.toString(),time.text.toString())
        }
        alertdialog.setNegativeButton("Cancel"){dialoginterface,i ->

        }
        alertdialog.show()
    }
}