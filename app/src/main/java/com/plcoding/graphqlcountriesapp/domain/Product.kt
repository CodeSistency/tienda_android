package com.plcoding.graphqlcountriesapp.domain

data class Product(
    val titulo: String,
    val precio: Double,
    val descuento: Int?,
    val destacado: Boolean,
    val imgPincipal: String

)