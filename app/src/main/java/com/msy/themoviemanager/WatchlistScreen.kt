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
import com.msy.themoviemanager.database.WatchlistDatabase
import com.msy.themoviemanager.database.WatchlistEntity
import com.msy.themoviemanager.databinding.FragmentWatchlistScreenBinding

class WatchlistScreen : Fragment() {

    private var _binding : FragmentWatchlistScreenBinding? = null
    private val watchlistScreenBinding get() = _binding!!

    lateinit var recyclerView: RecyclerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentWatchlistScreenBinding.inflate(inflater, container, false)

        recyclerView = watchlistScreenBinding.moviesListRecyclerWatchlist
        recyclerView.layoutManager = LinearLayoutManager(watchlistScreenBinding.root.context)

        recyclerView.setHasFixedSize(true)

        recyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(watchlistScreenBinding.moviesListRecyclerWatchlist.context)
        }


        attachWatchlistList()

        requireActivity().onBackPressedDispatcher.addCallback(this){

            val navBuilder = NavOptions.Builder()

            Navigation.findNavController(watchlistScreenBinding.root).navigate(R.id.searchScreen, null, navBuilder.build())

        }

        back()

        return watchlistScreenBinding.root
    }

    private fun attachWatchlistList(){
        val watchlistDatabase: WatchlistDatabase? = WatchlistDatabase.getWatchlistDatabase(watchlistScreenBinding.root.context)
        val movies: ArrayList<WatchlistEntity> = watchlistDatabase?.watchlistDAO()?.getAllMovies() as ArrayList<WatchlistEntity>

        val adapter = WatchlistAdapter(movies)
        {view, moviesWatchlistDTO ->  }

        recyclerView.adapter = adapter
    }

    private fun back(){
        watchlistScreenBinding.watchlistToolbarBack.setOnClickListener {
            val navBuilder = NavOptions.Builder()
            Navigation.findNavController(watchlistScreenBinding.root).navigate(R.id.searchScreen, null, navBuilder.build())
        }
    }

    override fun onResume() {
        attachWatchlistList()
        super.onResume()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()

    }
}