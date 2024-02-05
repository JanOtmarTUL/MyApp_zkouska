package com.example.myapp_zkouska

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView

class CarNotesAdapter(private var notes: List<CarNote>, context: Context): RecyclerView.Adapter<CarNotesAdapter.CarNoteViewHolder>() {

    private val db: CarDatabaseHelper = CarDatabaseHelper(context)

    class CarNoteViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        val nazevTextView: TextView = itemView.findViewById(R.id.tvNazev)
        val spzTextView: TextView = itemView.findViewById(R.id.tvSPZ)
        val dateSTKview: TextView = itemView.findViewById(R.id.tvDateStk)
        val dateReminderView: TextView = itemView.findViewById(R.id.tvDateReminder)
        val poznamkaView: TextView = itemView.findViewById(R.id.tvPoznamka)
        val updateButton: ImageView = itemView.findViewById(R.id.btnUpdate)
        val deleteButton: ImageView = itemView.findViewById(R.id.btnDelete)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CarNoteViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.car_item, parent, false)
        return CarNoteViewHolder(view)

    }

    override fun getItemCount(): Int = notes.size

    override fun onBindViewHolder(holder: CarNoteViewHolder, position: Int) {
        val note = notes[position]
        holder.nazevTextView.text = note.nazev
        holder.spzTextView.text = note.spz
        holder.dateSTKview.text = note.dateStk
        holder.dateReminderView.text = note.dateReminder
        holder.poznamkaView.text = note.myNote

        holder.updateButton.setOnClickListener {
            val intent = Intent(holder.itemView.context, UpdateCar::class.java).apply {
                putExtra("note_id", note.id)
            }
            holder.itemView.context.startActivity(intent)
        }

        holder.deleteButton.setOnClickListener {
            // Vytvoření dialogového okna
            AlertDialog.Builder(holder.itemView.context)
                .setTitle("Potvrzení smazání")
                .setMessage("Opravdu chcete smazat toto vozidlo?")
                .setPositiveButton("Ano") { _, _ ->
                    // Uživatel klikl na "Ano", provede se smazání
                    db.deleteNote(note.id)
                    refreshData(db.getAllNotes())
                    Toast.makeText(holder.itemView.context, "Vozidlo smazáno", Toast.LENGTH_SHORT).show()
                }
                .setNegativeButton("Ne") { dialog, _ ->
                    // Uživatel klikl na "Ne", dialog se zavře
                    dialog.dismiss()
                }
                .show()
        }
    }

    fun refreshData(newNotes: List<CarNote>){
        notes = newNotes
        notifyDataSetChanged()
    }
}
