package fr.app.pp_orders.ui.plate

import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import fr.app.pp_orders.R
import fr.app.pp_orders.model.PlateItem


import fr.app.pp_orders.ui.plate.PlateItemListFragment.PlateInteractionListener

import kotlinx.android.synthetic.main.fragment_plate_item.view.*

/**
 * [RecyclerView.Adapter] that can display a [PlateItem] and makes a call to the
 * specified [PlateInteractionListener].
 * Created by Mohamed Ali Bouzrati on 14/03/2020.
 */
class PlateRecyclerViewAdapter(
    private val mValues: List<PlateItem>,
    private val mListener: PlateInteractionListener?
) : RecyclerView.Adapter<PlateRecyclerViewAdapter.ViewHolder>() {

    private val mOnClickListener: View.OnClickListener

    init {
        mOnClickListener = View.OnClickListener { v ->
            val item = v.tag as PlateItem
            mListener?.onPlateSelected(item)
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
//        val mDescription: TextView = mView.description
        val mPrice: TextView = mView.price

        override fun toString(): String {
            return super.toString() + " '" + mName.text + "'"
        }
    }
}
