package com.example.todoapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapplication.databinding.ActivityNotepadItemsBinding

class NotepadItemsActivity : AppCompatActivity() {
    private lateinit var design:ActivityNotepadItemsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_notepad_items)
        design.notepadItemsObject = this@NotepadItemsActivity

    }
    fun fabClick(){
        val alertdialog = AlertDialog.Builder(this@NotepadItemsActivity)

        val designAlert = layoutInflater.inflate(R.layout.alert_deisgn_notepad,null)
        alertdialog.setTitle("Create Notepad")
        alertdialog.setIcon(R.drawable.desc)
        alertdialog.setView(designAlert)

        alertdialog.setPositiveButton("Ok"){dialoginterface,i ->
            startActivity(Intent(this@NotepadItemsActivity, NotepadContentActivity::class.java))
        }
        alertdialog.setNegativeButton("Cancel"){dialoginterface,i ->

        }
        alertdialog.show()

    }
}