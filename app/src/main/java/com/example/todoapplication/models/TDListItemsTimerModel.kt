package com.example.todoapplication.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapplication.classes.TDListItemWTimer
import com.example.todoapplication.classes.TDListWTimer
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TDListItemsTimerModel:ViewModel() {

    val db = FirebaseDatabase.getInstance()
    val refTDList = db.getReference("TDListItemWithTimer")
    var livedata = MutableLiveData<ArrayList<TDListItemWTimer>>()
    var Tdlist = ArrayList<TDListItemWTimer>()

    init {
        livedata = MutableLiveData<ArrayList<TDListItemWTimer>>()
    }

    fun TDListItemAddTimer(list_no: String,title:String,desc:String,time:String){
        val item = TDListItemWTimer(list_no,"",title,desc,time)
        refTDList.push().setValue(item)
    }

    fun TDListItemAllItems(list_no:String){
        refTDList.addValueEventListener(object :ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Tdlist.clear()
                for(t in snapshot.children){
                    val todo = t.getValue(TDListItemWTimer::class.java)
                    if (todo!=null){
                        if (todo.list_n.lowercase().contains(list_no.lowercase())){
                            todo.item_no = t.key!!
                            Tdlist.add(todo)
                        }
                    }
                }
                livedata.value = Tdlist
            }
            override fun onCancelled(error: DatabaseError) {

            }
        })
    }
    fun TDListItemDelete(item_no:String){
        refTDList.child(item_no).removeValue()
    }



}