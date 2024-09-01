package com.example.todoapplication

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapplication.classes.Notepad
import com.example.todoapplication.databinding.ActivityNotepadContentBinding
import com.example.todoapplication.models.NotepadModel

class NotepadContentActivity : AppCompatActivity() {
    private lateinit var design: ActivityNotepadContentBinding
    private val viewmodel:NotepadModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_notepad_content)
        design.notepadContentObject = this@NotepadContentActivity
        val note = intent.getSerializableExtra("note") as Notepad
        design.note = note
        design.title = note.note_title
        design.content = note.note_content
    }
    fun contentText(note_id:String,text:String){
        viewmodel.editNoteContent(note_id,text)
    }



}