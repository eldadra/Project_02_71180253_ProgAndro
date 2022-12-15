package com.example.notes

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.notes.databinding.ActivityEditNoteBinding
import com.example.notes.databinding.ActivityNoteBinding

class EditNoteActivity : AppCompatActivity() {

    lateinit var binding: ActivityEditNoteBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityEditNoteBinding.inflate(layoutInflater)
        super.onCreate(savedInstanceState)
        setContentView(binding.root)

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
    }
}