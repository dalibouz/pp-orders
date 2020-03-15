package fr.app.pp_orders.ui

import android.app.AlertDialog
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import fr.app.pp_orders.R
import fr.app.pp_orders.model.CategoryItem
import fr.app.pp_orders.model.PlateItem
import fr.app.pp_orders.network.PlateCategoriesApi
import fr.app.pp_orders.ui.category.CategoryInteractionListener
import fr.app.pp_orders.ui.category.CategoryListFragment
import fr.app.pp_orders.ui.plate.OnPlateDetailsInteractionListener
import fr.app.pp_orders.ui.plate.PlateDetailsDialog
import fr.app.pp_orders.ui.plate.PlateItemListFragment
import fr.app.pp_orders.ui.shoppingList.ShoppingListAdapterListener
import fr.app.pp_orders.ui.shoppingList.ShoppingListFragment
import fr.app.pp_orders.ui.shoppingList.ShoppingListInteractionListener
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.collections.ArrayList
import kotlin.collections.MutableList
import kotlin.collections.MutableMap

class MainActivity : AppCompatActivity(), PlateItemListFragment.PlateInteractionListener,
    CategoryInteractionListener, OnPlateDetailsInteractionListener,
    ShoppingListInteractionListener {

    companion object {
        var mFab: FloatingActionButton? = null
        var ITEMS: MutableList<CategoryItem> = ArrayList()
        val ITEM_MAP: MutableMap<String, CategoryItem> = HashMap()

        val shoppingList: MutableList<PlateItem> = ArrayList()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setSupportActionBar(toolbar)
        toolbar.title = title

        mFab = fab
        fab.setOnClickListener { view ->
            if (shoppingList.isEmpty()) {
                Toast.makeText(this, "Vous n'avez encore rien commandé !", Toast.LENGTH_LONG).show()
            } else {
                val fragment = ShoppingListFragment()
                supportFragmentManager
                    .beginTransaction()
                    .add(R.id.frameLayout, fragment)
                    .addToBackStack(ShoppingListFragment.TAG)
                    .commit()
            }

        }

        fetchCategories()
        chargeCategoryFragment()
    }

    private fun fetchCategories() {
        val categoriesAPI = PlateCategoriesApi.retrofit.create(PlateCategoriesApi::class.java)
        val plates = categoriesAPI.categooriesItems
        plates.enqueue(object : Callback<ArrayList<CategoryItem>> {
            override fun onResponse(
                call: Call<ArrayList<CategoryItem>>,
                response: Response<ArrayList<CategoryItem>>
            ) {
                android.util.Log.v("test", " response : ${response.body()}")
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }

            override fun onFailure(call: Call<ArrayList<CategoryItem>>, t: Throwable) {
                android.util.Log.v("test", " error $t")
                // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
            }
        })
    }

    private fun chargeCategoryFragment() {
        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, CategoryListFragment())
            .commit()
    }

    override fun onValidateCommande() {
        // TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onAskToRemovePlateFromShoppingList(
        plate: PlateItem,
        listener: ShoppingListAdapterListener
    ) {
        AlertDialog.Builder(this)
            .setTitle(plate.name)
            .setPositiveButton("Supprimer") { _, _ ->
                run {
                    shoppingList.remove(plate)
                    if (shoppingList.size == 0) {
                        this.onBackPressed()
                    } else {
                        listener.invalidateList()
                    }
                }
            }
            .create()
            .show()
    }

    override fun onAddPlateToShoppingList(plate: PlateItem) {
        Toast.makeText(baseContext, "Add ${plate.name} to ShopingList", Toast.LENGTH_SHORT).show()
        shoppingList.add(plate)
    }

    override fun onCategorySelected(category: CategoryItem) {
        val fragment = PlateItemListFragment.newInstance(category.name)

        supportFragmentManager
            .beginTransaction()
            .add(R.id.frameLayout, fragment)
            .addToBackStack(PlateItemListFragment.TAG)
            .commit()
    }

    override fun onPlateSelected(plate: PlateItem?) {
        val dialog = PlateDetailsDialog.newInstance(plate, this)
        dialog.show(this.supportFragmentManager, PlateDetailsDialog.TAG)
    }
}