package com.example.myapp_zkouska

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class ActivityStaniceStk : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_stanice_stk)

        val recyclerView: RecyclerView = findViewById(R.id.staniceRecyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        val adapter = StaniceAdapter(dbStanice.dbList)
        recyclerView.adapter = adapter

    }
}