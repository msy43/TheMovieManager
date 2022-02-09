package com.msy.themoviemanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.navigation.NavOptions
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msy.themoviemanager.database.FavoritesDatabase
import com.msy.themoviemanager.database.FavoritesEntity
import com.msy.themoviemanager.databinding.FragmentFavoritesScreenBinding


class FavoritesScreen : Fragment() {

    private var _binding : FragmentFavoritesScreenBinding? = null
    private val favoritesScreenBinding get() = _binding!!

    lateinit var recyclerView: RecyclerView


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentFavoritesScreenBinding.inflate(inflater, container, false)

        recyclerView = favoritesScreenBinding.moviesListRecyclerFavorites

        recyclerView.layoutManager = LinearLayoutManager(favoritesScreenBinding.root.context)
        recyclerView.setHasFixedSize(true)

        recyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(favoritesScreenBinding.moviesListRecyclerFavorites.context)
        }

        attachFavoritesList()

        requireActivity().onBackPressedDispatcher.addCallback(this){

            val navBuilder = NavOptions.Builder()

            Navigation.findNavController(favoritesScreenBinding.root).navigate(R.id.searchScreen, null, navBuilder.build())

        }

        back()

        return favoritesScreenBinding.root
    }

    private fun attachFavoritesList(){
        val favoritesDatabase: FavoritesDatabase? = FavoritesDatabase.getFavoritesDatabase(favoritesScreenBinding.root.context)

        val movies: ArrayList<FavoritesEntity> = favoritesDatabase?.favoritesDAO()?.getAllMovies() as ArrayList<FavoritesEntity>

        val adapter = FavoritesAdapter(movies)
        { view, moviesFavoritesDTO ->}

        recyclerView.adapter = adapter
    }

    private fun back(){
        favoritesScreenBinding.favoritesToolbarBack.setOnClickListener {
            val navBuilder = NavOptions.Builder()
            Navigation.findNavController(favoritesScreenBinding.root).navigate(R.id.searchScreen, null, navBuilder.build())

        }
    }

    override fun onResume() {
        attachFavoritesList()
        super.onResume()
    }

    override fun onDestroy() {
        _binding = null
        super.onDestroy()
    }

}