package fr.app.pp_orders.ui.plate

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import fr.app.pp_orders.R
import fr.app.pp_orders.ui.MainActivity
import fr.app.pp_orders.model.CategoryItem
import fr.app.pp_orders.model.PlateItem

/**
 * Created by Mohamed Ali Bouzrati on 14/03/2020.
 */
class PlateItemListFragment : Fragment() {

    private var category: CategoryItem? = null

    private var listener: PlateInteractionListener? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_CATEGORY_ID)) {
                // Load the dummy content specified by the fragment arguments.
                // In a real-world scenario, use a Loader to load content from a content provider.
                category = MainActivity.ITEM_MAP[it.getString(ARG_CATEGORY_ID)]
                // TODO : change me
//                activity?.toolbar_layout?.title = category?.name
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_plate_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = LinearLayoutManager(context)
                adapter = PlateRecyclerViewAdapter(
                    category!!.plates,
                    listener
                )
            }
        }
        return view
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is PlateInteractionListener) {
            listener = context
        } else {
            throw RuntimeException(context.toString() + " must implement PlateInteractionListener")
        }
    }

    override fun onDetach() {
        super.onDetach()
        listener = null
    }


    interface PlateInteractionListener {
        fun onPlateSelected(plate: PlateItem?)
    }

    companion object {

        const val TAG = "PlateItemListFragment"
        const val ARG_CATEGORY_ID = "arg-category-id"

        @JvmStatic
        fun newInstance(itemId: String) =
            PlateItemListFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_CATEGORY_ID, itemId)
                }
            }
    }
}