package fr.app.pp_orders.model

import java.time.ZonedDateTime
import java.util.*

/**
 * Created by Mohamed Ali Bouzrati on 14/03/2020.
 */
data class CategoryItem(val id: String, val name: String, val lastUpdate: ZonedDateTime, val plates: List<PlateItem>) {
    override fun toString(): String = "$name $lastUpdate $plates"
}