package com.example.s13_briceno_cuchareableappgooglemaps.view

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Bundle
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.example.s13_briceno_cuchareableappgooglemaps.R
import com.example.s13_briceno_cuchareableappgooglemaps.model.ComidaSpot
import com.example.s13_briceno_cuchareableappgooglemaps.viewmodel.MainViewModel
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.android.material.bottomsheet.BottomSheetDialog

class MainActivity : AppCompatActivity(), OnMapReadyCallback {

    private lateinit var map: GoogleMap
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mapFragment = supportFragmentManager
            .findFragmentById(R.id.map) as SupportMapFragment
        mapFragment.getMapAsync(this)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        map = googleMap

        // Centro en la UNS
        val centroUNS = LatLng(-9.120841, -78.513068)
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(centroUNS, 16f))

        viewModel.spots.observe(this, Observer { spots ->
            spots.forEach { spot ->
                val ubicacion = LatLng(spot.latitud, spot.longitud)
                val marker = map.addMarker(
                    MarkerOptions()
                        .position(ubicacion)
                        .title(spot.nombre)
                        .snippet(spot.descripcion)
                        .icon(obtenerIconoPorTipo(spot.tipo))
                )
                marker?.tag = spot
            }
            val centroUNS = LatLng(-9.120841, -78.513068)
            map.moveCamera(CameraUpdateFactory.newLatLngZoom(centroUNS, 16f))
        })

        map.setOnMarkerClickListener { marker ->
            val spot = marker.tag as? ComidaSpot
            spot?.let { mostrarBottomSheet(it) }
            true
        }


    }

    private fun mostrarBottomSheet(spot: ComidaSpot) {
        val view = layoutInflater.inflate(R.layout.bottom_sheet_spot, null)

        val nombreText = view.findViewById<TextView>(R.id.tvNombreLugar)
        val descText = view.findViewById<TextView>(R.id.tvDescripcionLugar)

        nombreText.text = spot.nombre
        descText.text = spot.descripcion

        val bottomSheetDialog = BottomSheetDialog(this)
        bottomSheetDialog.setContentView(view)
        bottomSheetDialog.show()
    }

    private fun obtenerIconoPorTipo(tipo: String): BitmapDescriptor {
        val context = this
        val resId = when (tipo.lowercase()) {
            "churros" -> R.drawable.ic_churros
            "hamburguesa" -> R.drawable.ic_hamburguesa
            "bebida" -> R.drawable.ic_comida
            "salchipapa" -> R.drawable.ic_salchipapa
            "pan" -> R.drawable.ic_pan
            "cafe" -> R.drawable.ic_cafe
            "pizza" -> R.drawable.ic_pizza
            "jugo" -> R.drawable.ic_jugo
            else -> R.drawable.ic_comida
        }
        val originalBitmap = BitmapFactory.decodeResource(context.resources, resId)
        val scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, 80, 80, false)
        return BitmapDescriptorFactory.fromBitmap(scaledBitmap)
    }

}
