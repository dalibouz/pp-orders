package fr.app.pp_orders.ui.plate

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.DialogFragment
import fr.app.pp_orders.model.PlateItem
import java.io.Serializable

/**
 * Created by Mohamed Ali Bouzrati on 14/03/2020.
 */
class PlateDetailsDialog : DialogFragment() {

    private var plate: PlateItem? = null
    private var listener: OnPlateDetailsInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            plate = it.getSerializable(ARG_PLATE) as PlateItem
            listener = it.getSerializable(ARG_LISTENER) as OnPlateDetailsInteractionListener
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity.let {
            val myBuilder = AlertDialog.Builder(it)
            myBuilder
                .setTitle(plate?.name)
                .setPositiveButton("Ajouter (${plate?.price}€)") { _, _ ->
                    run {
                        plate?.let {
                            listener?.onAddPlateToShoppingList(it)
                        }
                    }
                }
            plate?.description?.let {
                if (it.isNotEmpty()) {
                    myBuilder.setMessage(plate?.description)
                } else {
                    myBuilder.setMessage("Pas de description")
                }
            }
            myBuilder.create()
        }
    }

    companion object {
        const val TAG = "PlateDetailsDialog"
        private const val ARG_PLATE = "argPlate"
        private const val ARG_LISTENER = "arglistener"

        fun newInstance(plate: PlateItem?, listener: OnPlateDetailsInteractionListener) =
            PlateDetailsDialog().apply {
                arguments = Bundle().apply {
                    putSerializable(ARG_PLATE, plate)
                    putSerializable(ARG_LISTENER, listener)
                }
            }
    }
}

interface OnPlateDetailsInteractionListener : Serializable {
    fun onAddPlateToShoppingList(plate: PlateItem)
}