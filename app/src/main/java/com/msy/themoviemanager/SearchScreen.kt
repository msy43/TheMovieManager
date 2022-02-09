package com.msy.themoviemanager

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import androidx.activity.addCallback
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.msy.themoviemanager.databinding.FragmentSearchScreenBinding
import com.msy.themoviemanager.models.Movies
import com.msy.themoviemanager.models.MoviesResponse
import com.msy.themoviemanager.services.MoviesApiService
import com.msy.themoviemanager.services.MoviesSearchApiInterface
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class SearchScreen : Fragment() {

    private var _binding : FragmentSearchScreenBinding? = null
    private val searchScreenBinding get() = _binding!!


    lateinit var recyclerView: RecyclerView

    private lateinit var searchBar: SearchView

    var movies = ArrayList<Movies>()
    val adapter = MoviesAdapter(movies) { view, moviesDTO ->

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {

        _binding = FragmentSearchScreenBinding.inflate(inflater, container, false)

        recyclerView = searchScreenBinding.moviesListRecycler
        searchBar = searchScreenBinding.searchButton

        recyclerView.layoutManager = LinearLayoutManager(searchScreenBinding.root.context)
        recyclerView.setHasFixedSize(true)

        searchBar.queryHint = "Search movie..."


        recyclerView.apply {
            recyclerView.layoutManager = LinearLayoutManager(recyclerView.context)
        }

        searchBar.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(p0: String?): Boolean {
                if(movies.isNotEmpty()){
                    movies.clear()
                    getMovieData(p0!!)
                }else{
                    getMovieData(p0!!)
                }

                return false
            }

            override fun onQueryTextChange(p0: String?): Boolean {
                return false
            }

        })

        requireActivity().onBackPressedDispatcher.addCallback(this){}


        return searchScreenBinding.root
    }


    private fun getMovieData(query :String) {


        val apiService = MoviesApiService.getInstance().create(MoviesSearchApiInterface::class.java)
        apiService.getMovieList(query).enqueue(object: Callback<MoviesResponse> {
            override fun onFailure(call: Call<MoviesResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<MoviesResponse>, response: Response<MoviesResponse>) {
                movies.addAll(response.body()!!.movies)

                recyclerView.adapter = adapter

            }
        })
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}