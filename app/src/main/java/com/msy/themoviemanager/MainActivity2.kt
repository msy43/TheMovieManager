package com.msy.themoviemanager

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.Navigation
import androidx.navigation.fragment.NavHostFragment
import com.msy.themoviemanager.databinding.ActivityMain2Binding


class MainActivity2 : AppCompatActivity() {

    private lateinit var binding: ActivityMain2Binding

    private var fragmentId : Int = 0

    var backButtonCount = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMain2Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)



        binding.navigationView.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.bottommenuSearch -> {
                    navigateBetweenFragment(R.id.searchScreen)
                    true
                }

                R.id.bottommenuWatchlist -> {
                    navigateBetweenFragment(R.id.watchlistScreen)
                    true
                }

                R.id.bottommenuFavorites -> {
                    navigateBetweenFragment(R.id.favoritesScreen)
                    true
                }
                else -> false
            }
        }

        getFragmentLabel()

    }

    private fun navigateBetweenFragment(id: Int){
        Navigation.findNavController(binding.fragmentContainerView).navigate(id)
    }

    private fun getFragmentLabel(){
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainerView) as NavHostFragment
        val navController = navHostFragment.navController



        navController.addOnDestinationChangedListener { _, destination, _ ->
            val id = destination.label.toString().toInt()
            val navigationView = binding.navigationView
            navigationView.menu.getItem(id).isChecked = true
            fragmentId = id


        }
    }

    override fun onBackPressed() {
        if (fragmentId == 0){
            if (backButtonCount >= 1) {
                finish()
            } else {
                Toast.makeText(this, "Press the back button once again to close the application.", Toast.LENGTH_SHORT).show()
                backButtonCount++
            }
        }
        super.onBackPressed()
    }
}