package com.example.todoapplication.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapplication.classes.TDListItemWOTimer
import com.example.todoapplication.classes.TDListItemWTimer
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TDListItemsWithoutTimerModel:ViewModel() {
    val db = FirebaseDatabase.getInstance()
    val refDataBase = db.getReference("TDListItemsWithoutTimer")
    var livedata = MutableLiveData<ArrayList<TDListItemWOTimer>>()
    var TDlist = ArrayList<TDListItemWOTimer>()

    init {
        livedata = MutableLiveData<ArrayList<TDListItemWOTimer>>()
    }

    fun TDListItemAddWithoutTimer(list_no: String,title:String,desc:String){
        val item = TDListItemWOTimer(list_no,"",title,desc)
        refDataBase.push().setValue(item)
    }

    fun TDListAllItemsWithoutTimer(list_no:String){
        refDataBase.addValueEventListener(object : ValueEventListener {
            override fun onDataChange(snapshot: DataSnapshot) {
                TDlist.clear()
                for(t in snapshot.children){
                    val todo = t.getValue(TDListItemWOTimer::class.java)
                    if (todo!=null){
                        if (todo.list_n.lowercase().contains(list_no.lowercase())){
                            todo.item_no = t.key!!
                            TDlist.add(todo)
                        }
                    }
                }
                livedata.value = TDlist
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    fun TDListITemDelete(list_n:String){
        refDataBase.child(list_n).removeValue()
    }

}