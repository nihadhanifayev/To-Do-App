package com.example.todoapplication

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.todoapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var design:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        design = DataBindingUtil.setContentView(this,R.layout.activity_main)
        design.mainActivityObject = this

    }
    fun goToToDoListWithTimer(){
        startActivity(Intent(this@MainActivity, TDListWithTimer::class.java))
    }
    fun goToToDoListWithOutTimer(){
        startActivity(Intent(this@MainActivity, TDListWithoutTimer::class.java))
    }
    fun goToNotepad(){
        startActivity(Intent(this@MainActivity, NotepadItemsActivity::class.java))
    }
}