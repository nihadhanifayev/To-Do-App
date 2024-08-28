package com.example.todoapplication.models

import android.util.Log
import android.view.View
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todoapplication.TDListWithTimer
import com.example.todoapplication.classes.TDListWTimer
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener

class TDListTimerModel:ViewModel() {
    val db = FirebaseDatabase.getInstance()
    val refTDList = db.getReference("TDListWithTimer")
    var livedata = MutableLiveData<ArrayList<TDListWTimer>>()
    var Tdlist = ArrayList<TDListWTimer>()

    init {
        livedata = MutableLiveData<ArrayList<TDListWTimer>>()
    }

    fun tdlistWithTimerAdd(title:String,date:String){
        val tdlist = TDListWTimer("",title,date)
        refTDList.push().setValue(tdlist)
    }
    fun tdlistWithTimerAllLists(infoText:View){
        refTDList.addValueEventListener(object:ValueEventListener{
            override fun onDataChange(snapshot: DataSnapshot) {
                Tdlist.clear()

                for(c in snapshot.children){
                    val list = c.getValue(TDListWTimer::class.java)
                    if (list != null){
                        Log.e("giris","tesdiq")
                        list.list_no = c.key!!
                        Tdlist.add(list)
                    }
                }
                livedata.value = Tdlist
            }
            override fun onCancelled(error: DatabaseError) {
            }
        })
    }
    fun tdlistWithTimerRemoveList(list_id:String){
        refTDList.child(list_id).removeValue()
    }
    fun tdlistTimerEdit(list_id: String,list_title:String,list_date:String){
        val updatelist = HashMap<String,Any>()
        updatelist["list_title"] = list_title
        updatelist["list_date"] = list_date
        refTDList.child(list_id).updateChildren(updatelist)
    }
}