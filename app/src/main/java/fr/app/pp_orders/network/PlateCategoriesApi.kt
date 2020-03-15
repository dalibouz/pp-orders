package fr.app.pp_orders.network

import fr.app.pp_orders.model.CategoryItem
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import java.util.*

/**
 * Created by Mohamed Ali Bouzrati on 15/03/2020.
 */
interface PlateCategoriesApi {

    @get:GET("PlateCategories/recent")
    val categooriesItems: Call<ArrayList<CategoryItem>>

    companion object {

        const val BASE_URL = "https://pp-order.cpos-strasbourg.fr/api/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}