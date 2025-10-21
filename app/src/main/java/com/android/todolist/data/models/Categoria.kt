package com.android.todolist.data.models
import com.android.todolist.R

data class Categoria(
    val id: String,
    val nombre: String,
    val iconRes: Int

)

// Categorías clásicas
object CategoriasPredefinidas {
    val lista = listOf(
        Categoria("restaurant", "Restaurant", R.drawable.ic_restaurant),
        Categoria("mascotas", "Mascotas", R.drawable.ic_pet),
        Categoria("farmacias", "Farmacias", R.drawable.ic_pharmacy),
        Categoria("tiendas", "Tiendas", R.drawable.ic_storefront),
        Categoria("cafeteria", "Cafeteria", R.drawable.ic_coffee),
        Categoria("parque", "Parque", R.drawable.ic_park),
        Categoria("museo", "Museo", R.drawable.ic_museum),
        Categoria(id = "patrimonial", "Patrimonio", R.drawable.ic_heritage),
        Categoria("supermercado", "Super", R.drawable.ic_store),
        Categoria("transporte", "Transporte", R.drawable.ic_bus),
        Categoria("salud", "Salud", R.drawable.ic_health),
        Categoria("teatro", "Teatro", R.drawable.ic_cinema),
        Categoria("hotel", "Hotel", R.drawable.ic_hotel),
        Categoria("educacion", "Metro", R.drawable.ic_subway),
        Categoria("deportes", "Deportes", R.drawable.ic_sports_gym),
        Categoria("botillerias", "Botillerias", R.drawable.ic_wine_bar),
        Categoria("otros", "Otros", R.drawable.ic_other)
    )
}