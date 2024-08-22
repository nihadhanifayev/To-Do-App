package com.example.todoapplication

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapplication.databinding.ActivityNotepadContentBinding

class NotepadContentActivity : AppCompatActivity() {
    private lateinit var design: ActivityNotepadContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_notepad_content)
        design.notepadContentObject = this@NotepadContentActivity


    }
}