package com.example.todoapplication

import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapplication.adapters.TDListItemAdapterWOTimer
import com.example.todoapplication.classes.TDListItemWOTimer
import com.example.todoapplication.classes.TDListWOTImer
import com.example.todoapplication.databinding.ActivityTdlistItemsWithoutTimerBinding
import com.example.todoapplication.models.TDListItemsWithoutTimerModel
import kotlinx.coroutines.newFixedThreadPoolContext

class TDListItemsWithoutTimer : AppCompatActivity() {
    private lateinit var design:ActivityTdlistItemsWithoutTimerBinding
    private val viewmodel:TDListItemsWithoutTimerModel by viewModels()
    private lateinit var List:TDListWOTImer
    private lateinit var TDListItem:ArrayList<TDListItemWOTimer>
    private lateinit var adapter:TDListItemAdapterWOTimer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_tdlist_items_without_timer)
        design.tdListItemWithoutTimer = this
        List = intent.getSerializableExtra("List") as TDListWOTImer
        design.listTitle = List.list_title
        viewmodel.TDListAllItemsWithoutTimer(List.list_no)
        viewmodel.livedata.observe(this,{list->
            TDListItem = list
            design.tdListItemArray = list
            adapter = TDListItemAdapterWOTimer(this,TDListItem,viewmodel)
            design.adapter = adapter
        })

    }
    fun fabClick(){
        val alertdialog = AlertDialog.Builder(this@TDListItemsWithoutTimer)

        val designAlert = layoutInflater.inflate(R.layout.alert_design_list_item_without_timer,null)
        var title = designAlert.findViewById(R.id.editTextAlertItemTitleWithoutTimer) as EditText
        var desc = designAlert.findViewById(R.id.editTextAlertDescWithoutTimer) as EditText
        alertdialog.setTitle("To-Do List Item Create")
        alertdialog.setIcon(R.drawable.desc)
        alertdialog.setView(designAlert)

        alertdialog.setPositiveButton("Ok"){dialoginterface,i ->
            viewmodel.TDListItemAddWithoutTimer(List.list_no,title.text.toString(),desc.text.toString())
        }
        alertdialog.setNegativeButton("Cancel"){dialoginterface,i ->

        }
        alertdialog.show()
    }
}