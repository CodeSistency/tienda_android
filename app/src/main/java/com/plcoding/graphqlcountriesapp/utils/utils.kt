package com.plcoding.graphqlcountriesapp.utils

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.plcoding.graphqlcountriesapp.domain.model.products.ProductCart
import com.plcoding.graphqlcountriesapp.domain.model.products.toCartString
import com.plcoding.graphqlcountriesapp.domain.model.products.toProductCartList
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.jsoup.Jsoup
import org.jsoup.nodes.Document

const val CART_PREFS_KEY = "cart_prefs_key"

fun saveCartToSharedPreferences(cart: List<ProductCart>, context: Context) {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        CART_PREFS_KEY,
        Context.MODE_PRIVATE
    )
//    val cartString = Json.encodeToString(cart)
//    val cartString = Json.encodeToString(cart.toCartString())
    val cartString = cart.toCartString()


    Log.e("Cart updated shared preferences", cartString)
    sharedPreferences.edit().putString(CART_PREFS_KEY, cartString).apply()
}

fun getCartFromSharedPreferences(context: Context): List<ProductCart> {
    val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        CART_PREFS_KEY,
        Context.MODE_PRIVATE
    )
    val cartString = sharedPreferences.getString(CART_PREFS_KEY, "")
    Log.e("Cart from shared preferences", cartString?.toProductCartList().toString())
    return cartString?.toProductCartList() ?: emptyList()
}
//fun formatH() {
//    val unformattedHtml = "<html><head><title>Sample</title></head><body><p>Hello, <b>World</b>!</p></body></html>"
//
//    val formattedHtml = formatHtml(unformattedHtml)
//    println(formattedHtml)
//}

fun formatHtml(html: String): String {
    val document = Jsoup.parse(html)
    val outputSettings = Document.OutputSettings().prettyPrint(true)

    return document.outputSettings(outputSettings).outerHtml()
}