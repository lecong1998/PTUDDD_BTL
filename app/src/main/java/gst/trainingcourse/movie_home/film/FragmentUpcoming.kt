package gst.trainingcourse.movie_home.film

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import gst.trainingcourse.movie_home.DetailFilm.DetailActivity
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.entity.TopMovie.Film
import gst.trainingcourse.movie_home.entity.intheater.Item
import gst.trainingcourse.movie_home.home.*
import gst.trainingcourse.movie_home.repository.MovieRepository
import kotlinx.android.synthetic.main.fragmentupcoming.*

class FragmentUpcoming : Fragment(),HomeContract.View ,OnCLickItem_ComingSoon{

    private lateinit var presenter: HomeContract.Presenter
    private lateinit var adapter_comingsoon: HomeComingSoonAdapter

    private var username :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        arguments.let {
            username = it?.getString("username")
        }
    }

    companion object {
        fun newInstance(username : String) = FragmentUpcoming().apply {
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
        return  inflater.inflate(R.layout.fragmentupcoming,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = HomePresenter(this, MovieRepository(requireContext()))

        presenter.getComingSoonmovie()
        adapter_comingsoon = HomeComingSoonAdapter(this)
        recycleview_upcomming?.layoutManager = GridLayoutManager(activity,2, GridLayoutManager.VERTICAL,false)

        recycleview_upcomming.adapter = adapter_comingsoon

    }

    override fun updateViewDataMostPopularMovie(dataList: List<Film>) {
        TODO("Not yet implemented")
    }

    override fun updateViewDataIntheater(datalistIntheater: List<Item>) {
        TODO("Not yet implemented")
    }

    override fun updateViewDatapopular(datalistmostpopular: List<gst.trainingcourse.movie_home.entity.MostPopular.Item>) {
        TODO("Not yet implemented")
    }

    override fun updateViewDataComingSoon(dataListcomingsoon: List<gst.trainingcourse.movie_home.entity.comingsoon.Item>) {
        adapter_comingsoon.setData(dataListcomingsoon)
        recycleview_upcomming.adapter?.notifyDataSetChanged()
        upcomming_progressbar.visibility = View.GONE
    }

    override fun OnClickItem_ComingSoonMovie(item: gst.trainingcourse.movie_home.entity.comingsoon.Item) {
        val intent = Intent(context, DetailActivity::class.java)
        intent.putExtra("id_movie",item.id.toString())
        intent.putExtra("username",username)
        startActivity(intent)
    }
}