package com.example.myapp_zkouska

import android.content.ContentValues
import android.content.Context
import android.database.sqlite.SQLiteDatabase
import android.database.sqlite.SQLiteOpenHelper

class CarDatabaseHelper(context: Context): SQLiteOpenHelper(context, DATABASE_NAME, null, DATABASE_VERSION){

    companion object{
        private const val DATABASE_NAME = "carnotesapp.db"
        private const val DATABASE_VERSION = 1
        private const val TABLE_NAME = "allcarnotes"
        private const val COLUMN_ID = "id"
        private const val COLUMN_NAZEV = "nazev"
        private const val COLUMN_SPZ = "spz"
        private const val COLUMN_DATESTK = "dateStk"
        private const val COLUMN_DATEREMINDER = "dateReminder"
        private const val COLUMN_MYNOTE = "myNote"
    }

    override fun onCreate(db: SQLiteDatabase?) {
        val createTableQuery = "CREATE TABLE $TABLE_NAME ($COLUMN_ID INTEGER PRIMARY KEY, $COLUMN_NAZEV TEXT, $COLUMN_SPZ TEXT, $COLUMN_DATESTK TEXT, $COLUMN_DATEREMINDER TEXT, $COLUMN_MYNOTE TEXT )"
        db?.execSQL(createTableQuery)
    }

    override fun onUpgrade(db: SQLiteDatabase?, oldVersion: Int, newVersion: Int) {
        val dropTableQuery = "DROP TABLE IF EXISTS $TABLE_NAME"
        db?.execSQL(dropTableQuery)
        onCreate(db)
    }

    fun insertCarNote(note: CarNote){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAZEV, note.nazev)
            put(COLUMN_SPZ, note.spz)
            put(COLUMN_DATESTK, note.dateStk)
            put(COLUMN_DATEREMINDER, note.dateReminder)
            put(COLUMN_MYNOTE, note.myNote)
        }
        db.insert(TABLE_NAME, null, values)
        db.close()
    }

    fun getAllNotes(): List<CarNote> {
      val carList = mutableListOf<CarNote>()
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME"
        val cursor = db.rawQuery(query, null)

        while (cursor.moveToNext()){
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
            val nazev = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAZEV))
            val spz = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SPZ))
            val dateStk = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATESTK))
            val dateRemider = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATEREMINDER))
            val myNote = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MYNOTE))

            val note = CarNote(id, nazev, spz, dateStk, dateRemider, myNote)
            carList.add(note)
        }
        cursor.close()
        db.close()
        return carList
    }

    fun updateCarNote(note: CarNote){
        val db = writableDatabase
        val values = ContentValues().apply {
            put(COLUMN_NAZEV, note.nazev)
            put(COLUMN_SPZ, note.spz)
            put(COLUMN_DATESTK, note.dateStk)
            put(COLUMN_DATEREMINDER, note.dateReminder)
            put(COLUMN_MYNOTE, note.myNote)
        }
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(note.id.toString())
        db.update(TABLE_NAME, values, whereClause, whereArgs)
        db.close()
    }

    fun getNoteByID(noteId: Int): CarNote {
        val db = readableDatabase
        val query = "SELECT * FROM $TABLE_NAME WHERE $COLUMN_ID = $noteId"
        val cursor = db.rawQuery(query, null)

        if (cursor.moveToFirst()) {
            val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        }

        val id = cursor.getInt(cursor.getColumnIndexOrThrow(COLUMN_ID))
        val nazev = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_NAZEV))
        val spz = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_SPZ))
        val dateStk = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATESTK))
        val dateRemider = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_DATEREMINDER))
        val myNote = cursor.getString(cursor.getColumnIndexOrThrow(COLUMN_MYNOTE))

        cursor.close()
        db.close()
        return CarNote(id, nazev, spz, dateStk, dateRemider, myNote)
    }

    fun deleteNote(noteId: Int){
        val db = writableDatabase
        val whereClause = "$COLUMN_ID = ?"
        val whereArgs = arrayOf(noteId.toString())
        db.delete(TABLE_NAME, whereClause, whereArgs)
        db.close()
    }
}