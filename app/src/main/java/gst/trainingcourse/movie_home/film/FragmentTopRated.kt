package gst.trainingcourse.movie_home.film

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import gst.trainingcourse.movie_home.Activity.FragmentSearch
import gst.trainingcourse.movie_home.DetailFilm.DetailActivity
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.entity.TopMovie.Film
import gst.trainingcourse.movie_home.entity.intheater.Item
import gst.trainingcourse.movie_home.home.HomeContract
import gst.trainingcourse.movie_home.home.HomePresenter
import gst.trainingcourse.movie_home.home.MovieAdapter
import gst.trainingcourse.movie_home.home.OnClickItem_TopMovie
import gst.trainingcourse.movie_home.repository.MovieRepository
import kotlinx.android.synthetic.main.fragmenttoprated.*

class FragmentTopRated: Fragment(),HomeContract.View,OnClickItem_TopMovie{

    private lateinit var presenter: HomeContract.Presenter
    private lateinit var adapter_topmovie : MovieAdapter

    private var username :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            username = it?.getString("username")
        }
    }

    companion object {
        fun newInstance(username : String) = FragmentTopRated().apply {
            arguments =Bundle().apply {
                putString("username",username)
            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragmenttoprated,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        adapter_topmovie = MovieAdapter(this)
        presenter = HomePresenter(this, MovieRepository(requireContext()))
        presenter.getMostPopularMovie()
        recycleview_toprated?.layoutManager = GridLayoutManager(activity,2, GridLayoutManager.VERTICAL,false)
        recycleview_toprated.adapter = adapter_topmovie

    }

    override fun updateViewDataMostPopularMovie(dataList: List<Film>) {
        adapter_topmovie.setData(dataList)
        recycleview_toprated.adapter?.notifyDataSetChanged()
        topmovie_progressbar.visibility = View.GONE
    }

    override fun updateViewDataIntheater(datalistIntheater: List<Item>) {
        TODO("Not yet implemented")
    }

    override fun updateViewDatapopular(datalistmostpopular: List<gst.trainingcourse.movie_home.entity.MostPopular.Item>) {
        TODO("Not yet implemented")
    }

    override fun updateViewDataComingSoon(dataListcomingsoon: List<gst.trainingcourse.movie_home.entity.comingsoon.Item>) {
        TODO("Not yet implemented")
    }

    override fun OnClickItem_Topmovie(film: Film) {
        val intent = Intent(activity, DetailActivity::class.java)
        intent.putExtra("id_movie",film.id)
        intent.putExtra("username",username)
        startActivity(intent)
    }

}