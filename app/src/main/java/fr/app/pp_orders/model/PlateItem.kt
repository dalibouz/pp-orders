package fr.app.pp_orders.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.Serializable

@Parcelize
data class PlateItem(
    val name: String,
    val description: String,
    val price: Double,
    val lastUpdate: String,
    val plateCategoryId: String
) :
    Serializable, Parcelable
