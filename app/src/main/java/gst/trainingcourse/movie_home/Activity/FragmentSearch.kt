package gst.trainingcourse.movie_home.Activity

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.Menu
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import gst.trainingcourse.movie_home.DetailFilm.DetailActivity
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.Search.OnCLickItem_Search
import gst.trainingcourse.movie_home.Search.SearchContract
import gst.trainingcourse.movie_home.Search.SearchPresenter
import gst.trainingcourse.movie_home.Search.adapter_search
import gst.trainingcourse.movie_home.entity.Search.Result
import gst.trainingcourse.movie_home.entity.Search.Search
import gst.trainingcourse.movie_home.repository.MovieRepository
import kotlinx.android.synthetic.main.fragment_search.*


class FragmentSearch : Fragment(),SearchContract.View, OnCLickItem_Search {

    private lateinit var adapter : adapter_search
    private lateinit var presenter : SearchContract.Presenter



    private var username :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments.let {
            username = it?.getString("username")
        }
    }

    companion object {
        fun newInstance(username : String) = FragmentSearch().apply {
            arguments =Bundle().apply {
                putString("username",username)
            }
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_search, container, false)
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = SearchPresenter(this, MovieRepository(requireContext()))
        adapter = adapter_search(this)

        searchview.setOnQueryTextListener(object : SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                search_progressbar.visibility = View.VISIBLE
                val hanler = Handler().postDelayed(Runnable {
                    presenter.getSearchTitle(query!!)

                },1000)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                search_progressbar.visibility = View.VISIBLE
                val hanler = Handler().postDelayed(Runnable {
                    presenter.getSearchTitle(newText!!)

                },1000)
                return false
            }
        })

        search_recyclerview?.layoutManager = GridLayoutManager(activity,2, GridLayoutManager.VERTICAL,false)

        search_recyclerview.adapter = adapter
    }

    override fun updateViewSearch(search: Search) {
        if (search.results != null)
        {
            adapter.setData(search.results!!)
            search_recyclerview.adapter?.notifyDataSetChanged()
            search_progressbar.visibility = View.GONE
        }

        search_progressbar.visibility = View.GONE
    }

    override fun ClickItem_Search(result: Result) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("id_movie",result.id)
        startActivity(intent)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)
        val item = menu.findItem(R.id.menu_search)
        item.isVisible = false
    }

}