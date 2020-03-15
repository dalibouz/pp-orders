package fr.app.pp_orders.ui

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import fr.app.pp_orders.R
import fr.app.pp_orders.model.CategoryItem
import fr.app.pp_orders.network.PpInterfaceApi
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class SplashScreenActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        immersiveMode()
        fetchCategories()
    }

    private fun immersiveMode() {
        window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION // hide nav bar

                or View.SYSTEM_UI_FLAG_FULLSCREEN // hide status bar

                or View.SYSTEM_UI_FLAG_IMMERSIVE)
    }

    private fun fetchCategories() {
        val categoriesAPI = PpInterfaceApi.retrofit.create(PpInterfaceApi::class.java)
        val plates = categoriesAPI.categooriesItems
        plates.enqueue(object : Callback<ArrayList<CategoryItem>> {
            override fun onResponse(
                call: Call<ArrayList<CategoryItem>>,
                response: Response<ArrayList<CategoryItem>>
            ) {
                if (response.body() != null) {
                    MainActivity.initData(response.body() as ArrayList<CategoryItem>)
                    proceedToMainActivity()
                } else {
                    showErrorAndFinish()
                }
            }

            override fun onFailure(call: Call<ArrayList<CategoryItem>>, t: Throwable) {
                showErrorAndFinish()
            }
        })
    }

    private fun proceedToMainActivity() {
        val SPLASH_TIME_OUT = 1000
        Handler().postDelayed({
            val i = Intent(this@SplashScreenActivity, MainActivity::class.java)
            startActivity(i)
            finish()
        }, SPLASH_TIME_OUT.toLong())
    }

    private fun showErrorAndFinish() {
        AlertDialog.Builder(this)
            .setTitle("Problème :/")
            .setMessage("Nous arrivons pas à récupérer la liste des plats.\nVeuillez nous excuser pour la gêne occasionnée et merci pour votre patience et votre compréhension.")
            .setPositiveButton("Ok") { _, _ ->
                run {
                    finish()
                }
            }
            .setCancelable(false)
            .create()
            .show()
    }
}
