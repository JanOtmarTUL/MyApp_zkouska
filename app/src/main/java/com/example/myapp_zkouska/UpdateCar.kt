package com.example.myapp_zkouska

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.EditText
import android.widget.Toast
import com.example.myapp_zkouska.databinding.ActivityUpdateCarBinding
import com.google.android.material.snackbar.Snackbar

class UpdateCar : AppCompatActivity() {

    private lateinit var binding: ActivityUpdateCarBinding
    private lateinit var db: CarDatabaseHelper
    private var noteId: Int = -1
    private lateinit var dateEditStk: EditText
    private lateinit var dateEditReminder: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUpdateCarBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val editText = findViewById<EditText>(R.id.etEditSPZ)

        editText.inputType = android.text.InputType.TYPE_CLASS_TEXT or
                android.text.InputType.TYPE_TEXT_FLAG_CAP_CHARACTERS

        editText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                //toto není potřeba implementovat
            }

            override fun onTextChanged(charSequence: CharSequence?, start: Int, before: Int, count: Int) {
                //toto není potřeba implementovat
            }

            override fun afterTextChanged(editable: Editable?) {
                // při zadávání SPZ převádí automaticky malá písma na velká
                editable?.let {
                    val upperCaseText = it.toString().toUpperCase()
                    if (it.toString() != upperCaseText) {
                        editText.setText(upperCaseText)
                        editText.setSelection(upperCaseText.length)
                    }
                }
            }
        })

        dateEditStk = findViewById(R.id.etEditDateSTK)

        dateEditStk.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //toto není potřeba implementovat
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //toto není potřeba implementovat
            }

            override fun afterTextChanged(s: Editable?) {
                // Po změně textu
                if (s != null) {
                    // Omezte délku textu na 10 znaků
                    if (s.length > 10) {
                        s.delete(10, s.length)
                    }

                    // Přidáme tečku po dvou zadaných znacích (např. DD.)
                    if (s.length == 2 && s[1] != '.') {
                        s.insert(2, ".")
                    }
                    // Přidáme tečku po pěti zadaných znacích (např. DD.MM.)
                    else if (s.length == 5 && s[4] != '.') {
                        s.insert(5, ".")
                    }
                }
            }
        })

        dateEditReminder = findViewById(R.id.etEditDateReminder)

        dateEditReminder.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
                //toto není potřeba implementovat
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                //toto není potřeba implementovat
            }

            override fun afterTextChanged(s: Editable?) {

                if (s != null) {

                    if (s.length > 10) {
                        s.delete(10, s.length)
                    }

                    if (s.length == 2 && s[1] != '.') {
                        s.insert(2, ".")
                    }
                    else if (s.length == 5 && s[4] != '.') {
                        s.insert(5, ".")
                    }
                }
            }
        })

        db = CarDatabaseHelper(this)

        noteId = intent.getIntExtra("note_id", -1)
        if(noteId == -1){
            finish()
            return
        }

        val note = db.getNoteByID(noteId)
        binding.etEditName.setText(note.nazev)
        binding.etEditSPZ.setText(note.spz)
        binding.etEditDateSTK.setText(note.dateStk)
        binding.etEditDateReminder.setText(note.dateReminder)
        binding.etEditCarNote.setText(note.myNote)

        binding.btnEditSaveCar.setOnClickListener{
            val newNazev = binding.etEditName.text.toString()
            val newSpz = binding.etEditSPZ.text.toString()
            val newDateStk = binding.etEditDateSTK.text.toString()
            val newDateReminder = binding.etEditDateReminder.text.toString()
            val newMyNote = binding.etEditCarNote.text.toString()
            val updatedNote = CarNote(noteId, newNazev, newSpz, newDateStk, newDateReminder, newMyNote)

            if (newNazev.isNotEmpty() && newSpz.isNotEmpty() && newDateStk.isNotEmpty() && newDateReminder.isNotEmpty()) {
                db.updateCarNote(updatedNote)
                finish()
                Toast.makeText(this, "Vozidlo upraveno", Toast.LENGTH_SHORT).show()
            }
            else{
                Snackbar.make(it,"Všechny povinné položky musí být vyplněny!", Snackbar.LENGTH_LONG).show()
            }
        }
    }
}