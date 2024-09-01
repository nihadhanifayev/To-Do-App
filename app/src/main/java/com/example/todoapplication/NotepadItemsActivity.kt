package com.example.todoapplication

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapplication.adapters.TDNotepadAdapter
import com.example.todoapplication.classes.Notepad
import com.example.todoapplication.databinding.ActivityNotepadItemsBinding
import com.example.todoapplication.models.NotepadModel

class NotepadItemsActivity : AppCompatActivity() {
    private lateinit var design:ActivityNotepadItemsBinding
    private val viewModel:NotepadModel by viewModels()
    private lateinit var noteList:ArrayList<Notepad>
    private lateinit var adapter:TDNotepadAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_notepad_items)
        design.notepadItemsObject = this@NotepadItemsActivity
        viewModel.allNotes()
        viewModel.livedata.observe(this,{list ->
            design.noteList = list
            noteList = list
            adapter = TDNotepadAdapter(this,noteList,viewModel)
            design.adapter = adapter
        })
    }
    fun fabClick(){
        val alertdialog = AlertDialog.Builder(this@NotepadItemsActivity)

        val designAlert = layoutInflater.inflate(R.layout.alert_deisgn_notepad,null)
        val title = designAlert.findViewById(R.id.NotepadAlertTitle) as EditText
        alertdialog.setTitle("Create Notepad")
        alertdialog.setIcon(R.drawable.desc)
        alertdialog.setView(designAlert)

        alertdialog.setPositiveButton("Ok"){dialoginterface,i ->
            viewModel.addNotes(title.text.toString())
        }
        alertdialog.setNegativeButton("Cancel"){dialoginterface,i ->

        }
        alertdialog.show()

    }
}