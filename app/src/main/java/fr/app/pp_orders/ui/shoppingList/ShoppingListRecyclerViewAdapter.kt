package fr.app.pp_orders.ui.shoppingList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.app.pp_orders.R
import fr.app.pp_orders.model.PlateItem
import kotlinx.android.synthetic.main.fragment_plate_item.view.*

/**
 * Created by Mohamed Ali Bouzrati on 14/03/2020.
 */
class ShoppingListRecyclerViewAdapter(
    private val mValues: List<PlateItem>,
    private val mListener: ShoppingListInteractionListener?
) : RecyclerView.Adapter<ShoppingListRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as PlateItem
            mListener?.onAskToRemovePlateFromShoppingList(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_plate_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = mValues[position]
        holder.mName.text = item.name
        holder.mPrice.text = "${item.price} €"

        with(holder.mView) {
            tag = item
            setOnClickListener(mOnClickListener)
        }
    }

    override fun getItemCount(): Int = mValues.size

    inner class ViewHolder(val mView: View) : RecyclerView.ViewHolder(mView) {
        val mName: TextView = mView.name
        val mPrice: TextView = mView.price

        override fun toString(): String {
            return super.toString() + " '" + mName.text + "'"
        }
    }
}