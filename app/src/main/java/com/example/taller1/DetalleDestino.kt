package com.example.taller1

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query



class DetalleDestino : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_destino)
        setupUI()
    }

    private fun setupUI() {
        val destino = intent.getStringExtra("detalleDestino")?.let { JSONObject(it) }
        destino?.let {
            detallesDestino(it)
            servicioClima(it)
            botonFavoritos(it)
        }
    }

    private fun detallesDestino(destino: JSONObject) {
        findViewById<TextView>(R.id.nombreDestino).text = destino.getString("nombre")
        findViewById<TextView>(R.id.paisDestino).text = destino.getString("pais")
        findViewById<TextView>(R.id.categoriadestino).text = destino.getString("categoria")
        findViewById<TextView>(R.id.plandestino).text = destino.getString("plan")
        findViewById<TextView>(R.id.preciodestino).text = "USD ${destino.getString("precio")}"
    }

    private fun servicioClima(destino: JSONObject) {
        val retrofit = Retrofit.Builder()
            .baseUrl("http://my.meteoblue.com/") // URL base
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        val service = retrofit.create(WeatherService::class.java)
        val apiKey = "tGT7PBshFgjy3A9O"
        val latitud = destino.getDouble("latitud")
        val longitud = destino.getDouble("longitud")

        buscarDatosDelClima(service, latitud, longitud, apiKey)
    }

    private fun buscarDatosDelClima(service: WeatherService, latitud: Double, longitud: Double, apiKey: String) {
        lifecycleScope.launch {
            try {
                val response = service.getWeatherData(latitud, longitud, apiKey)
                if (response.isSuccessful) {
                    val responseBody = response.body()?.string()
                    val temperatura = JSONObject(responseBody ?: "").getJSONObject("data_xmin").getJSONArray("temperature").getDouble(0)
                    findViewById<TextView>(R.id.temperaturaActual).text = "${temperatura}C"
                } else {
                    mostrarError(response.errorBody()?.string())
                }
            } catch (e: Exception) {
                mostrarError(e.message)
            }
        }
    }

    private fun botonFavoritos(destino: JSONObject) {
        findViewById<Button>(R.id.bagfavoritos).setOnClickListener {
            it.isEnabled = false // Desactivar el botón
            MainActivity.favoritos.add(destino.getString("nombre"))
            Toast.makeText(this, "Añadido a favoritos", Toast.LENGTH_SHORT).show()
        }
    }

    private fun mostrarError(errorMessage: String?) {
        Toast.makeText(this, "Error: $errorMessage", Toast.LENGTH_LONG).show()
    }
}

interface WeatherService {
    @GET("packages/basic-5min")
    suspend fun getWeatherData(
        @Query("lat") latitude: Double,
        @Query("lon") longitude: Double,
        @Query("apikey") apiKey: String
    ): Response<ResponseBody>
}
