package fr.app.pp_orders.model

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * Created by Mohamed Ali Bouzrati on 15/03/2020.
 */
@Parcelize
data class PlateOrder(val number: Int, val _plate: PlateItem) : Parcelable