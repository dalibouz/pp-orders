package fr.app.pp_orders.ui.category

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import fr.app.pp_orders.R
import fr.app.pp_orders.model.CategoryItem
import kotlinx.android.synthetic.main.fragment_category_item.view.*

class CategoryRecyclerViewAdapter(
    private val values: List<CategoryItem>,
    private val mListener: CategoryInteractionListener?
) : RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder>() {

    private val onClickListener: View.OnClickListener

    init {
        onClickListener = View.OnClickListener { v ->
            val item = v.tag as CategoryItem
            mListener?.onCategorySelected(item)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_category_item, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.title.text = item.name
        val imageId = getImageByName(item.name)
        holder.imageView.setImageResource(imageId)

        with(holder.itemView) {
            tag = item
            setOnClickListener(onClickListener)
        }
    }

    private fun getImageByName(name: String): Int {
        return when (name) {
            "Accompagnements" -> R.mipmap.ic_accompagnement
            "Boissons" -> return R.mipmap.ic_boisson
            "Desserts" -> return R.mipmap.ic_dessert
            "Plats du Jour" -> return R.mipmap.ic_plat_dj
            "Salades" -> return R.mipmap.ic_salade
            "Sandwichs" -> return R.mipmap.ic_sandwich
            "Sandwichs Chauds " -> return R.mipmap.ic_sandwich_chaud
            "Soupes" -> return R.mipmap.ic_soupe
            else -> R.mipmap.ic_launcher
        }
    }

    override fun getItemCount() = values.size

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val title: TextView = view.title
        val imageView: ImageView = view.imageView
    }
}