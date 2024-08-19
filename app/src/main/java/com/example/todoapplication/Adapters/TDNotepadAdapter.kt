package com.example.todoapplication.Adapters

import android.content.Context
import android.media.Image
import android.provider.ContactsContract.CommonDataKinds.Note
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.Classes.Notepad
import com.example.todoapplication.R

class TDNotepadAdapter(private var mContext:Context,private var NoteList:List<Notepad>):RecyclerView.Adapter<TDNotepadAdapter.CardDesignObjects>() {

    inner class CardDesignObjects(design: View):RecyclerView.ViewHolder(design){
        var cardViewNotepad:CardView
        var textViewTitle:TextView
        var imageViewDelete:ImageView

        init {
            cardViewNotepad = design.findViewById(R.id.cardViewNotepad)
            textViewTitle = design.findViewById(R.id.textViewNotepadItemTitle)
            imageViewDelete = design.findViewById(R.id.imageViewMenuNotepad)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardDesignObjects {
        var design = LayoutInflater.from(mContext).inflate(R.layout.card_design_notepad_items,parent,false)
        return CardDesignObjects(design)
    }

    override fun getItemCount(): Int {
        return NoteList.size
    }

    override fun onBindViewHolder(holder: CardDesignObjects, position: Int) {
        var note = NoteList.get(position)
    }

}