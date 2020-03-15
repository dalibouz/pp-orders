package fr.app.pp_orders.ui.shoppingList

import android.content.Context
import android.content.Intent
import android.content.IntentFilter
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import fr.app.pp_orders.model.PlateItem
import java.io.Serializable

/**
 * Created by Mohamed Ali Bouzrati on 15/03/2020.
 */
class ShoppingListBroadcast {

    companion object {
        val ACTION_PLATE_REMOVED = "ACTION_PLATE_REMOVED"
        val PARAM_PLATE = "PARAM_PLATE"

        fun makeIntentFilter(): IntentFilter {
            val intentFilter = IntentFilter()
            intentFilter.addAction(ACTION_PLATE_REMOVED)
            return intentFilter
        }

        fun broadcastPlateRemoved(plate: PlateItem, context: Context) {
            val intent = Intent()
            intent.action = ACTION_PLATE_REMOVED
            intent.putExtra(PARAM_PLATE, plate as Serializable)
            LocalBroadcastManager.getInstance(context).sendBroadcast(intent)
        }
    }
}