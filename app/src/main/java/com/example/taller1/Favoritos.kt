package com.example.taller1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class Favoritos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favoritos)

        val listaFavoritos = findViewById<ListView>(R.id.listafavoritos)
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, MainActivity.favoritos)
        listaFavoritos.adapter = adapter
    }

    override fun onResume() {
        super.onResume()
        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, MainActivity.favoritos)
        findViewById<ListView>(R.id.listafavoritos).adapter = adapter
        adapter.notifyDataSetChanged()
    }
}