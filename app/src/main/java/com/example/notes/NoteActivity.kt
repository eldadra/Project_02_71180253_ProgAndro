package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.notes.databinding.ActivityNoteBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class NoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityNoteBinding
    var firestore: FirebaseFirestore ?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        firestore = FirebaseFirestore.getInstance()

        // tanggal
        val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
        val current = LocalDateTime.now().format(formatter)

        binding.tvTanggalNote.text = current.toString()

        binding.btnSave.setOnClickListener {
            val title = binding.etTitleNote.text.toString()
            val tanggal = binding.tvTanggalNote.text.toString()
            val isi = binding.etNote.text.toString()

            val note = Note(title,tanggal,isi)
            firestore?.collection("Notes")?.document(tanggal)?.set(note)
            Toast.makeText(this, "Berhasil Disimpan", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnDelete.setOnClickListener {

        }
    }
}