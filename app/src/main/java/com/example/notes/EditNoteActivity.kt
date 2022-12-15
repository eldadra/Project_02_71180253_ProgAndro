package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes.databinding.ActivityEditNoteBinding
import com.example.notes.databinding.ActivityNoteBinding
import com.google.firebase.firestore.FirebaseFirestore
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class EditNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditNoteBinding
    var firestore: FirebaseFirestore?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

        supportActionBar?.hide()

        firestore = FirebaseFirestore.getInstance()

        val judul = intent.getStringExtra("judul").toString()
        val tanggal = intent.getStringExtra("tanggal").toString()
        val isi = intent.getStringExtra("isi").toString()

        val title = binding.etTitleNote
        title.setText(judul)
        val date = binding.tvTanggalNote
        date.setText(tanggal)
        val content = binding.etNote
        content.setText(isi)

        binding.btnHome.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }

        binding.btnSaveEdit.setOnClickListener {
            val judul = binding.etTitleNote.text.toString()
            // tanggal
            val formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")
            val current = LocalDateTime.now().format(formatter)

            val tanggalBaru = current.toString()
            val isi = binding.etNote.text.toString()
            firestore?.collection("Notes")?.document(tanggal)
                ?.update(mapOf(
                "tanggal" to tanggalBaru,
                "judul" to judul,
                "isi" to isi,
            ))
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}