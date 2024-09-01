package com.example.todoapplication.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.ImageView
import android.widget.PopupMenu
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.todoapplication.NotepadContentActivity
import com.example.todoapplication.classes.Notepad
import com.example.todoapplication.R
import com.example.todoapplication.models.NotepadModel
import kotlin.concurrent.thread

class TDNotepadAdapter(private val mContext:Context,private var NoteList:List<Notepad>,private val viewmodel:NotepadModel):RecyclerView.Adapter<TDNotepadAdapter.CardDesignObjects>() {

    inner class CardDesignObjects(design: View):RecyclerView.ViewHolder(design){
        var cardViewNotepad:CardView
        var textViewTitle:TextView
        var imageViewMenu:ImageView

        init {
            cardViewNotepad = design.findViewById(R.id.cardViewNotepad)
            textViewTitle = design.findViewById(R.id.textViewNotepadItemTitle)
            imageViewMenu = design.findViewById(R.id.imageViewMenuNotepad)
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
        holder.textViewTitle.text = note.note_title
        holder.cardViewNotepad.setOnClickListener {
            val intent = Intent(mContext,NotepadContentActivity::class.java)
            intent.putExtra("note",note)
            mContext.startActivity(intent)
        }
        holder.imageViewMenu.setOnClickListener {
            val popupMenu = PopupMenu(mContext,holder.imageViewMenu)
            popupMenu.menuInflater.inflate(R.menu.tdlist_menu_detail,popupMenu.menu)
            popupMenu.setOnMenuItemClickListener(PopupMenu.OnMenuItemClickListener { item ->
                when(item.itemId) {
                    R.id.action_delete -> {
                        viewmodel.deleteNote(note.note_id)
                        true
                    }

                    R.id.action_edit -> {
                        val alertdialog = AlertDialog.Builder(mContext)
                        val designAlert = LayoutInflater.from(mContext)
                            .inflate(R.layout.alert_deisgn_notepad, null)
                        var title =
                            designAlert.findViewById(R.id.NotepadAlertTitle) as EditText
                        alertdialog.setTitle("Edit Note")
                        alertdialog.setIcon(R.drawable.desc)
                        alertdialog.setView(designAlert)

                        alertdialog.setPositiveButton("Ok") { dialoginterface, i ->
                            viewmodel.editNote(note.note_id,title.text.toString())
                        }
                        alertdialog.setNegativeButton("Cancel") { dialoginterface, i ->
                        }
                        alertdialog.show()
                        true
                    }

                    else -> {
                        false
                    }
                }
            })
            popupMenu.show()
        }
    }
}
