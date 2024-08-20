package com.example.todoapplication.Activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import com.example.todoapplication.R
import com.example.todoapplication.databinding.ActivityNotepadContentBinding
import com.example.todoapplication.databinding.ActivityTdlistWithTimerBinding

class NotepadContentActivity : AppCompatActivity() {
    private lateinit var design: ActivityNotepadContentBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_notepad_content)
    }
}