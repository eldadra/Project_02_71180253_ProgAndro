package com.example.notes

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView


class NoteAdapter (val listNote: ArrayList<Note>, var context: Context): RecyclerView.Adapter<NoteAdapter.NoteHolder>() {
    class NoteHolder(val v: View): RecyclerView.ViewHolder(v) {
        fun bindView(note: Note, context: Context) {
            v.findViewById<TextView>(R.id.tvTitleRcy).text = note.judul
            v.findViewById<TextView>(R.id.tvTanggalRcy).text = note.tanggal
            v.findViewById<TextView>(R.id.tvIsiRcy).text = note.isi
            v.setOnClickListener {
                Toast.makeText(v.context, "${note.judul}: ${note.tanggal}", Toast.LENGTH_SHORT).show()
                val intent = Intent(context, EditNoteActivity::class.java)
                intent.putExtra("judul",note.judul)
                intent.putExtra("tanggal",note.tanggal)
                intent.putExtra("isi",note.isi)
                context.startActivity(intent)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NoteHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_note, parent, false)
        return NoteHolder(v)
    }

    override fun onBindViewHolder(holder: NoteAdapter.NoteHolder, position: Int) {
        holder.bindView(listNote[position], context)
    }

    override fun getItemCount(): Int {
        return listNote.size
    }


}