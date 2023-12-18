package com.plcoding.graphqlcountriesapp.domain.model.products

import kotlinx.serialization.Serializable
//import kotlinx.serialization.decodeFromString
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json

@Serializable
data class ProductCart(
    val productId: String,
    val title: String,
    val description: String,
    val category: String,
    val price: Double,
    val imgPrincipal: String,
    val selectedTalla: String,
    val selectedColor: String,
    val quantity: Int
)

fun List<ProductCart>.toCartString(): String {
    return Json.encodeToString(this)
}

fun String.toProductCartList(): List<ProductCart> {
    return try {
        Json.decodeFromString(this)
    } catch (e: Exception) {
        emptyList()
    }
}

//@Serializable
//data class ProductCart(
//    val productId: String,
//    val title: String,
//    val description: String,
//    val category: String,
//    val price: Double,
//    val imgPrincipal: String,
//    val selectedTalla: String,
//    val selectedColor: String,
//    val quantity: Int
//)
//fun List<ProductCart>.toCartString(): String {
//    return Json.encodeToString(this)
//}
//fun String.toProductCartList(): List<ProductCart> {
//    return try {
//        Json.decodeFromString(this)
//    } catch (e: Exception) {
//        emptyList()
//    }
//}

