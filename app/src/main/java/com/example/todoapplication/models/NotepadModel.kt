package com.example.todoapplication.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapplication.NotepadItemsActivity
import com.example.todoapplication.classes.Notepad
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class NotepadModel:ViewModel() {

    var db = FirebaseDatabase.getInstance()
    var refDatabase = db.getReference("Notes")
    var livedata = MutableLiveData<ArrayList<Notepad>>()
    var notes = ArrayList<Notepad>()

    init {
        livedata = MutableLiveData<ArrayList<Notepad>>()
    }

    fun addNotes(title:String){
        var note = Notepad("",title,"")
        refDatabase.push().setValue(note)
    }

    fun allNotes(){
        refDatabase.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                notes.clear()
                for (n in snapshot.children){
                    val Note = n.getValue(Notepad::class.java)
                    if (Note!=null){
                        Note.note_id = n.key!!
                        notes.add(Note)
                    }
                }
                livedata.value = notes
            }

            override fun onCancelled(error: DatabaseError) {

            }

        })
    }
    fun deleteNote(note_no:String){
        refDatabase.child(note_no).removeValue()
    }
    fun editNote(note_id: String,note_title:String){
        val updatenote = HashMap<String,Any>()
        updatenote["note_title"] = note_title
        refDatabase.child(note_id).updateChildren(updatenote)
    }
    fun editNoteContent(note_id: String,note_content:String){
        val updatenoteContent = HashMap<String,Any>()
        updatenoteContent["note_content"] = note_content
        refDatabase.child(note_id).updateChildren(updatenoteContent)
    }


}