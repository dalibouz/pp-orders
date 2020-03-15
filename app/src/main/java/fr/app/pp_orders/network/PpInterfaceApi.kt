package fr.app.pp_orders.network

import fr.app.pp_orders.model.CategoryItem
import fr.app.pp_orders.model.Order
import fr.app.pp_orders.model.UserOrder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import java.util.*

/**
 * Created by Mohamed Ali Bouzrati on 15/03/2020.
 */
interface PpInterfaceApi {

    @get:GET("PlateCategories/recent")
    val categooriesItems: Call<ArrayList<CategoryItem>>

    @get:GET("Orders/today")
    val todayOrder: Call<Order>

    @get:GET("Orders/incoming")
    val incomingOrders: Call<ArrayList<Order>>

    @POST("Orders/userOrders")
    fun createOrder(@Body userOrder: UserOrder): Observable

    companion object {

        const val BASE_URL = "https://pp-order.cpos-strasbourg.fr/api/"

        val retrofit = Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}