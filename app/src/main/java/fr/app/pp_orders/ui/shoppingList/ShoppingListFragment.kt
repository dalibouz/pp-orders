package fr.app.pp_orders.ui.shoppingList

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.localbroadcastmanager.content.LocalBroadcastManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.app.pp_orders.R
import fr.app.pp_orders.model.PlateItem
import fr.app.pp_orders.ui.MainActivity
import fr.app.pp_orders.ui.shoppingList.ShoppingListBroadcast.Companion.ACTION_PLATE_REMOVED

class ShoppingListFragment : Fragment() {
    private var listener: ShoppingListInteractionListener? = null
    lateinit var totalAmount: TextView
    lateinit var mAdapter: ShoppingListRecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        LocalBroadcastManager.getInstance(context!!)
            .registerReceiver(broadCastReceiver, ShoppingListBroadcast.makeIntentFilter())
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_shopping_list, container, false)
        val listView = view.findViewById<RecyclerView>(R.id.list)
        mAdapter = ShoppingListRecyclerViewAdapter(MainActivity.shoppingList, listener)

        // Set the adapter
        if (listView is RecyclerView) {
            with(listView) {
                layoutManager = LinearLayoutManager(context)
                adapter = mAdapter
            }
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        totalAmount = view.findViewById(R.id.totalAmount)
        setTotalAmountValue()
    }

    private fun setTotalAmountValue() {
        var totalAmountValue = 0.0
        MainActivity.shoppingList.forEach {
            totalAmountValue += it.price
        }
        totalAmount.text = "$totalAmountValue"
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

    override fun onDestroy() {
        super.onDestroy()
        LocalBroadcastManager.getInstance(context!!).unregisterReceiver(broadCastReceiver)
    }

    fun onButtonPressed(uri: Uri) {
        listener?.onValidateCommande()
    }

    fun onPlateRemoved() {
        setTotalAmountValue()
        mAdapter.notifyDataSetChanged()
    }

    companion object {
        const val TAG = "ShoppingListFragment"
    }

    val broadCastReceiver = object : BroadcastReceiver() {
        override fun onReceive(contxt: Context?, intent: Intent?) {
            when (intent?.action) {
                ACTION_PLATE_REMOVED -> onPlateRemoved()
            }
        }
    }
}

interface ShoppingListInteractionListener {
    fun onValidateCommande()
    fun onAskToRemovePlateFromShoppingList(plate: PlateItem)
}