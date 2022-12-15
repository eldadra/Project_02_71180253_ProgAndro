package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.notes.databinding.ActivityMainBinding
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.QuerySnapshot

class MainActivity : AppCompatActivity() {

    lateinit var binding: ActivityMainBinding
    var firestore: FirebaseFirestore?= null

    private lateinit var recyclerView: RecyclerView
    private lateinit var listNote: ArrayList<Note>
    private lateinit var noteAdapter: NoteAdapter


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        recyclerView = findViewById(R.id.rcyNotes)
        recyclerView.layoutManager = LinearLayoutManager(this)

        listNote = arrayListOf()

        noteAdapter = NoteAdapter(listNote,this)
        recyclerView.adapter = noteAdapter

        EventChangeListener()

        binding.btnAdd.setOnClickListener {
            val intent = Intent(this,NoteActivity::class.java)
            startActivity(intent)
        }
    }

    private fun EventChangeListener() {
        firestore = FirebaseFirestore.getInstance()
        firestore?.collection("Notes")
            ?.addSnapshotListener(object : EventListener<QuerySnapshot>{
                override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
                    if (error != null) {
                        Log.e("Firestore error", error.message.toString())
                        return
                    }

                    for(dc: DocumentChange in value?.documentChanges!!) {
                        if(dc.type == DocumentChange.Type.ADDED) {
                            listNote.add(dc.document.toObject(Note::class.java))
                        }
                    }
                    noteAdapter.notifyDataSetChanged()
                }

            })
    }

}