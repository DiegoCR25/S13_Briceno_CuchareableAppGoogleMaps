package com.example.s13_briceno_cuchareableappgooglemaps.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.s13_briceno_cuchareableappgooglemaps.model.ComidaSpot

class MainViewModel : ViewModel() {

    private val _spots = MutableLiveData<List<ComidaSpot>>()
    val spots: LiveData<List<ComidaSpot>> = _spots

    init {
        cargarSpots()
    }

    private fun cargarSpots() {
        _spots.value = listOf(
            ComidaSpot("Churros Don Pepe", -9.118020, -78.513732, "Churros calientes y rellenos.", "churros"),
            ComidaSpot("Hamburguesas La Esquina", -9.117181, -78.515696, "Hamburguesas en la esquina de Av. Brasil.", "hamburguesa"),
            ComidaSpot("Chocho Express", -9.118430, -78.511257, "Refrescos al instante.", "bebida"),
            ComidaSpot("Antojitos Mary", -9.117713, -78.515124, "Papas, salchipapas, y comida rapida.", "salchipapa"),
            ComidaSpot("Pan con palta La Uni", -9.118067, -78.515046, "Pan con algo", "pan"),
            ComidaSpot("Café al paso", -9.120348, -78.513589, "Café en vaso térmico. Muy bueno.", "cafe"),
            ComidaSpot("MiniPizza Express", -9.119506, -78.509416, "Mini pizzas.", "pizza"),
            ComidaSpot("Jugos Naturales Anita", -9.116165, -78.510174, "Jugos naturales y sánguches frescos.", "jugo"),

        )

    }
}
