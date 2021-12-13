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
import gst.trainingcourse.movie_home.home.HomeContract
import gst.trainingcourse.movie_home.home.HomePopularAdapter
import gst.trainingcourse.movie_home.home.HomePresenter
import gst.trainingcourse.movie_home.home.OnCLickItem_Popular
import gst.trainingcourse.movie_home.repository.MovieRepository
import kotlinx.android.synthetic.main.fragmentpopular.*

class FragmentPopular : Fragment(),HomeContract.View , OnCLickItem_Popular{


    private lateinit var presenter: HomeContract.Presenter
    private lateinit var adapter_popular: HomePopularAdapter

    private var username :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            username = it?.getString("username")
        }
    }

    companion object {
        fun newInstance(username : String) = FragmentPopular().apply {
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
        return inflater.inflate(R.layout.fragmentpopular,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter_popular = HomePopularAdapter(this)
        presenter = HomePresenter(this, MovieRepository(requireContext()))
        presenter.getpopularmovie()
        recycleview_popular?.layoutManager = GridLayoutManager(activity,2, GridLayoutManager.VERTICAL,false)
        recycleview_popular.adapter = adapter_popular

    }

    override fun updateViewDataMostPopularMovie(dataList: List<Film>) {
        TODO("Not yet implemented")
    }

    override fun updateViewDataIntheater(datalistIntheater: List<Item>) {
        TODO("Not yet implemented")
    }

    override fun updateViewDatapopular(datalistmostpopular: List<gst.trainingcourse.movie_home.entity.MostPopular.Item>) {
        adapter_popular.setData(datalistmostpopular)
        recycleview_popular.adapter?.notifyDataSetChanged()
        popular_progressbar.visibility = View.GONE
    }

    override fun updateViewDataComingSoon(dataListcomingsoon: List<gst.trainingcourse.movie_home.entity.comingsoon.Item>) {
        TODO("Not yet implemented")
    }

    override fun OnClickItem_MostPopularMovie(item: gst.trainingcourse.movie_home.entity.MostPopular.Item) {
        val intent = Intent(context,DetailActivity::class.java)
        intent.putExtra("id_movie",item.id.toString())
        intent.putExtra("username",username)
        startActivity(intent)
    }
}