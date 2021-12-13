package gst.trainingcourse.movie_home.film

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import gst.trainingcourse.movie_home.DetailFilm.DetailActivity
import gst.trainingcourse.movie_home.OnClickItem
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.entity.TopMovie.Film
import gst.trainingcourse.movie_home.entity.intheater.Item
import gst.trainingcourse.movie_home.home.HomeContract
import gst.trainingcourse.movie_home.adapter.HomeIntheaterAdapter
import gst.trainingcourse.movie_home.home.HomePresenter
import gst.trainingcourse.movie_home.repository.MovieRepository
import kotlinx.android.synthetic.main.fragmentnowplaying.*

class FragmentNowPlaying : Fragment(),HomeContract.View,OnClickItem {

    private lateinit var presenter: HomeContract.Presenter
    private lateinit var adapter_intheater: HomeIntheaterAdapter


    private var username :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            username = it?.getString("username")
        }
    }

    companion object {
        fun newInstance(username : String) = FragmentNowPlaying().apply {
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
        return inflater.inflate(R.layout.fragmentnowplaying,container,false)
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = HomePresenter(this, MovieRepository(requireContext()))
        presenter.getIntheaterMovie()
        adapter_intheater = HomeIntheaterAdapter(this)

        recycleview_nowplaying?.layoutManager = GridLayoutManager(activity,2, GridLayoutManager.VERTICAL,false)
        recycleview_nowplaying.adapter = adapter_intheater

    }

    override fun updateViewDataMostPopularMovie(dataList: List<Film>) {
        TODO("Not yet implemented")
    }

    override fun updateViewDataIntheater(datalistIntheater: List<Item>) {
        adapter_intheater.setData(datalistIntheater)
        recycleview_nowplaying.adapter?.notifyDataSetChanged()
        nowplaying_progressbar.visibility = View.GONE
    }

    override fun updateViewDatapopular(datalistmostpopular: List<gst.trainingcourse.movie_home.entity.MostPopular.Item>) {
        TODO("Not yet implemented")
    }

    override fun updateViewDataComingSoon(dataListcomingsoon: List<gst.trainingcourse.movie_home.entity.comingsoon.Item>) {
        TODO("Not yet implemented")
    }

    override fun OnClickItem_IntheaterMovie(item: Item) {
        val intent = Intent(activity,DetailActivity::class.java)
        intent.putExtra("id_movie",item.id.toString())
        intent.putExtra("username",username)
        startActivity(intent)
    }
}