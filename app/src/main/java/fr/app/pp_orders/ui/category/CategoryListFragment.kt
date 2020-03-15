package fr.app.pp_orders.ui.category

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.app.pp_orders.R
import fr.app.pp_orders.dummy.CategoryDummyContent
import fr.app.pp_orders.model.CategoryItem

/**
 * Created by Mohamed Ali Bouzrati on 14/03/2020.
 */
class CategoryListFragment : Fragment() {

    private var listener: CategoryInteractionListener? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_category_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = CategoryRecyclerViewAdapter(
                    CategoryDummyContent.ITEMS,
                    listener
                )
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is CategoryInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement CategoryInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }
}

interface CategoryInteractionListener {
    fun onCategorySelected(category: CategoryItem)
}