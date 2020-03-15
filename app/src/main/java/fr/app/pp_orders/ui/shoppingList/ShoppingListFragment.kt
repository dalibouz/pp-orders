package fr.app.pp_orders.ui.shoppingList

import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.app.pp_orders.R
import fr.app.pp_orders.model.PlateItem
import fr.app.pp_orders.ui.MainActivity

class ShoppingListFragment : Fragment() {

    private var listener: ShoppingListInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shopping_list, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.list)

        // Set the adapter
        if (listView is RecyclerView) {
            with(listView) {
                layoutManager = LinearLayoutManager(context)
                adapter = ShoppingListRecyclerViewAdapter(
                    MainActivity.shoppingList,
                    listener
                )
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        MainActivity.mFab?.hide()
        if (context is ShoppingListInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement ShoppingListInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
        MainActivity.mFab?.show()
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onValidateCommande()
    }

    companion object {
        const val TAG = "ShoppingListFragment"
    }
}

interface ShoppingListInteractionListener {
    fun onValidateCommande()
    fun onAskToRemovePlateFromShoppingList(plate: PlateItem, listener: ShoppingListAdapterListener)
}