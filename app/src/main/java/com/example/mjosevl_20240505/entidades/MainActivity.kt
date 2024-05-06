package com.example.mjosevl_20240505.entidades

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.mjosevl_20240505.R
import com.example.mjosevl_20240505.database.DatabaseHelper

class MainActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var visitorsAdapter: VisitorsAdapter
    private lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout)

        databaseHelper = DatabaseHelper(this)
        setupRecyclerView()
        setupButtonListener()
    }

    private fun setupRecyclerView() {
        recyclerView = findViewById(R.id.rvVisitors)
        recyclerView.layoutManager = LinearLayoutManager(this)
        visitorsAdapter = VisitorsAdapter(listOf())
        recyclerView.adapter = visitorsAdapter
        refreshVisitorList()
    }

    private fun setupButtonListener() {
        val btnAddVisitor = findViewById<Button>(R.id.btnAddVisitor)
        btnAddVisitor.setOnClickListener {
            // Inicia la actividad AddVisitorActivity
            val intent = Intent(this, AddVisitorActivity::class.java)
            startActivity(intent)
        }
    }

    private fun refreshVisitorList() {
        val visitorList = databaseHelper.getAllVisitors()
        Log.d("MainActivity", "Loaded visitors: ${visitorList.size}")
        visitorsAdapter.visitors = visitorList
        visitorsAdapter.notifyDataSetChanged()
    }

    override fun onResume() {
        super.onResume()
        refreshVisitorList()
    }

}
