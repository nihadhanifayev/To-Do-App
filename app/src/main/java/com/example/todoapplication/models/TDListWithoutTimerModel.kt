package com.example.todoapplication.models

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapplication.TDListWithoutTimer
import com.example.todoapplication.classes.TDListWOTImer
import com.example.todoapplication.classes.TDListWTimer
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TDListWithoutTimerModel:ViewModel() {
    val db = FirebaseDatabase.getInstance()
    val refDatabase = db.getReference("TDListWithoutTimer")
    var livedata = MutableLiveData<ArrayList<TDListWOTImer>>()
    var TDList = ArrayList<TDListWOTImer>()

    init {
        livedata = MutableLiveData<ArrayList<TDListWOTImer>>()
    }

    fun tdListAddList(title:String){
        val List =  TDListWOTImer("",title)
        refDatabase.push().setValue(List)
    }

    fun tdListAllList(){
        refDatabase.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                TDList.clear()

                for(c in snapshot.children){
                    val list = c.getValue(TDListWOTImer::class.java)
                    if (list != null){
                        list.list_no = c.key!!
                        TDList.add(list)
                    }
                }
                livedata.value = TDList
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    fun deleteList(list_no:String){
        refDatabase.child(list_no).removeValue()
    }
    fun tdlistWithoutTimerEdit(list_id: String,list_title:String){
        val updatelist = HashMap<String,Any>()
        updatelist["list_title"] = list_title
        refDatabase.child(list_id).updateChildren(updatelist)
    }

}