package com.example.taller1


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import retrofit2.Response
import org.json.JSONArray
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



class ListaDestinos : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_lista_destinos)

        val listaDestinos = findViewById<ListView>(R.id.listadestinos)
        val destinosString = intent.getStringExtra("destinosFiltrados")
        val destinos = JSONArray(destinosString)

        val nombresDestinos = obtenerNombresDestinos(destinos)

        val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, nombresDestinos)
        listaDestinos.adapter = adapter

       configurarClickListener(listaDestinos, destinos)
    }

    private fun obtenerNombresDestinos(destinos: JSONArray): MutableList<String> {
        val nombresDestinos = mutableListOf<String>()
        for (i in 0 until destinos.length()) {
            val destino = destinos.getJSONObject(i)
            nombresDestinos.add(destino.getString("nombre"))
        }
        return nombresDestinos
    }

    private fun configurarClickListener(listaDestinos: ListView, destinos: JSONArray) {
        listaDestinos.setOnItemClickListener { _, _, position, _ ->
            val destinoSeleccionado = destinos.getJSONObject(position)
            val intent = Intent(this, DetalleDestino::class.java).apply {
                putExtra("detalleDestino", destinoSeleccionado.toString())
            }
            startActivity(intent)
        }
    }
}