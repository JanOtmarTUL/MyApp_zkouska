package com.example.myapp_zkouska

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapp_zkouska.databinding.ActivityVozidlaBinding

class ActivityVozidla : AppCompatActivity() {

    private lateinit var binding: ActivityVozidlaBinding
    private lateinit var db: CarDatabaseHelper
    private lateinit var notesAdapter: CarNotesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityVozidlaBinding.inflate(layoutInflater)
        setContentView(binding.root)

        db = CarDatabaseHelper(this)
        notesAdapter = CarNotesAdapter(db.getAllNotes(),this)

        binding.carNoteRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.carNoteRecyclerView.adapter = notesAdapter

        binding.btnAddCar.setOnClickListener(){
            val intent = Intent(this, AddCar::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        notesAdapter.refreshData(db.getAllNotes())
    }
}