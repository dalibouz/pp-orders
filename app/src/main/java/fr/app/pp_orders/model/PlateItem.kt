package fr.app.pp_orders.model

import java.io.Serializable
import java.time.ZonedDateTime

data class PlateItem(val name: String, val description: String, val price: Double, val lastUpdate: ZonedDateTime, val plateCategoryId: String) :
    Serializable
