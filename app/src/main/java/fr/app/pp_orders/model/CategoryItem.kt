package fr.app.pp_orders.model

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * Created by Mohamed Ali Bouzrati on 14/03/2020.
 */
@Parcelize
data class CategoryItem(
    @SerializedName("name") var name: String,
    @SerializedName("lastUpdate") var lastUpdate: String,
    @SerializedName("plates") var plates: List<PlateItem>
) : Parcelable