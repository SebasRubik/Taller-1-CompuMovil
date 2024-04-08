package com.example.taller1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import org.json.JSONObject

class Recomendaciones : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_recomendaciones)

        val detalleDestinoString = intent.getStringExtra("detalleDestino")
        configurarVistasConDestino(detalleDestinoString)

    }

    private fun configurarVistasConDestino(detalleDestinoString: String?) {
        val nombreDestinoTextView = findViewById<TextView>(R.id.rnombreDestino)
        val paisDestinoTextView = findViewById<TextView>(R.id.rpaisDestino)
        val categoriaDestinoTextView = findViewById<TextView>(R.id.rcategoriadestino)
        val planDestinoTextView = findViewById<TextView>(R.id.rplandestino)
        val precioDestinoTextView = findViewById<TextView>(R.id.rpreciodestino)

        if (detalleDestinoString != null && detalleDestinoString != "NA") {
            val destino = JSONObject(detalleDestinoString)

            nombreDestinoTextView.text = destino.getString("nombre")
            paisDestinoTextView.text = destino.getString("pais")
            categoriaDestinoTextView.text = destino.getString("categoria")
            planDestinoTextView.text = destino.getString("plan")
            precioDestinoTextView.text = "Precio: ${destino.getString("precio")}"
        } else {
            manejarSinRecomendaciones(nombreDestinoTextView, paisDestinoTextView, categoriaDestinoTextView, planDestinoTextView, precioDestinoTextView)
        }
    }

    private fun manejarSinRecomendaciones(nombreDestinoTextView: TextView, paisDestinoTextView: TextView, categoriaDestinoTextView: TextView, planDestinoTextView: TextView, precioDestinoTextView: TextView) {
        nombreDestinoTextView.text = "NA"
        paisDestinoTextView.text = "NA"
        categoriaDestinoTextView.text = "NA"
        planDestinoTextView.text = "NA"
        precioDestinoTextView.text = "NA"
    }
}