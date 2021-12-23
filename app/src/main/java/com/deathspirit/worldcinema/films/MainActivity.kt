package com.deathspirit.worldcinema

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    lateinit var filmsRCV: RecyclerView
    private val filmsList = ArrayList<films>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        filmsRCV = findViewById(R.id.filmsRCV)
        filmsRCV.layoutManager = LinearLayoutManager(this, RecyclerView.VERTICAL, false)
        val filmsClassAdapter = filmsAdapter(someClassList, this)
    }
}