package com.example.taller1

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Spinner
import org.json.JSONArray
import org.json.JSONObject
import java.nio.charset.Charset
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    companion object {
        val favoritos = mutableListOf<String>()
    }

    private var destinosJson = JSONArray()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        cargarJson()
        inicializarComponentesUI()
    }


    private fun cargarJson() {
        val inputStream = assets.open("destinos.json")
        val size = inputStream.available()
        val buffer = ByteArray(size)
        inputStream.read(buffer)
        inputStream.close()
        val json = String(buffer, Charset.defaultCharset())
        val jsonObject = JSONObject(json)
        destinosJson = jsonObject.getJSONArray("destinos")
    }

    private fun filtrarDestinosPorCategoria(categoria: String): JSONArray {
        val destinosFiltrados = JSONArray()
        for (i in 0 until destinosJson.length()) {
            val destino = destinosJson.getJSONObject(i)
            if (categoria == "Todos" || destino.getString("categoria") == categoria) {
                destinosFiltrados.put(destino)
            }
        }
        return destinosFiltrados
    }

    private fun encontrarCategoriaMasFrecuente(): String {
        val categorias = favoritos.mapNotNull { nombreDestino ->
            var categoriaDestino: String? = null
            for (i in 0 until destinosJson.length()) {
                val destino = destinosJson.getJSONObject(i)
                if (destino.getString("nombre") == nombreDestino) {
                    categoriaDestino = destino.getString("categoria")
                    break
                }
            }
            categoriaDestino
        }
        return categorias.groupingBy { it }.eachCount().maxByOrNull { it.value }?.key ?: "NA"
    }

    private fun inicializarComponentesUI() {
        val btn1 = findViewById<Button>(R.id.bedestinos)
        val btn2 = findViewById<Button>(R.id.bfavoritos)
        val btn3 = findViewById<Button>(R.id.brecomendaciones)

        val spinner = findViewById<Spinner>(R.id.scategorias)

        configurarBotones(btn1, btn2, btn3, spinner)
    }

    private fun configurarBotones(btn1: Button, btn2: Button, btn3: Button, spinner: Spinner) {
        val intent = Intent(applicationContext, ListaDestinos::class.java)
        val intent2 = Intent(applicationContext, Favoritos::class.java)
        val intent3 = Intent(applicationContext, Recomendaciones::class.java)

        btn1.setOnClickListener { accionBotonDestinos(spinner, intent) }
        btn2.setOnClickListener { startActivity(intent2) }
        btn3.setOnClickListener { accionBotonRecomendaciones(intent3) }
    }

    private fun accionBotonDestinos(spinner: Spinner, intent: Intent) {
        val categoriaSeleccionada = spinner.selectedItem.toString()
        val destinosFiltrados = filtrarDestinosPorCategoria(categoriaSeleccionada)
        intent.putExtra("destinosFiltrados", destinosFiltrados.toString())
        startActivity(intent)
    }

    private fun accionBotonRecomendaciones(intent3: Intent) {
        if (favoritos.isNotEmpty()) {
            val categoriaMasFrecuente = encontrarCategoriaMasFrecuente()
            val destinosFiltrados = filtrarDestinosPorCategoria(categoriaMasFrecuente)
            val destinoAleatorio = favoritos[Random.nextInt(favoritos.size)]
            for (i in 0 until destinosFiltrados.length()) {
                val destino = destinosFiltrados.getJSONObject(i)
                if (destino.getString("nombre") == destinoAleatorio) {
                    intent3.putExtra("detalleDestino", destino.toString())
                    break
                }
            }
        } else {
            // Manejar el caso de que no haya favoritos
            intent3.putExtra("detalleDestino", "NA")
        }
        startActivity(intent3)
    }


}