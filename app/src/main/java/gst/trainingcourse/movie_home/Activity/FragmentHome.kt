package gst.trainingcourse.movie_home.Activity

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import gst.trainingcourse.movie_home.DetailFilm.DetailActivity
import gst.trainingcourse.movie_home.OnClickItem
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.adapter.ViewflipperAdapter
import gst.trainingcourse.movie_home.entity.TopMovie.Film
import gst.trainingcourse.movie_home.entity.comingsoon.Item
import gst.trainingcourse.movie_home.home.*
import gst.trainingcourse.movie_home.repository.MovieRepository
import kotlinx.android.synthetic.main.activity_main.*

class FragmentHome : Fragment(), HomeContract.View,OnClickItem_TopMovie,OnCLickItem_Popular,OnCLickItem_ComingSoon ,OnClickItem{


    private lateinit var presenter: HomeContract.Presenter
    private lateinit var adapter_topmovie: MovieAdapter
    private lateinit var adapter_popular: HomePopularAdapter
    private lateinit var adapter_comingsoon: HomeComingSoonAdapter

    private var username :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            username = it?.getString("username")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = HomePresenter(this, MovieRepository(requireContext()))
        presenter.getComingSoonmovie()
        presenter.getMostPopularMovie()
        presenter.getpopularmovie()
        presenter.getIntheaterMovie()


      //  viewflipper()
        viewpopular()
        viewTopMovie()
        viewComingSoon()


    }

    companion object {
        fun newInstance(username : String) = FragmentHome().apply {
            arguments =Bundle().apply {
                putString("username",username)
            }
        }
    }

    override fun updateViewDataMostPopularMovie(dataList: List<Film>) {
        val topmoviefilm  = ArrayList<Film>()
        for (i in 0 .. 9)
        {
           // topmoviefilm.add(dataList[i])
        }
        adapter_topmovie.setData(dataList)
        home_recyclerview_topmovie.adapter?.notifyDataSetChanged()
    }

    override fun updateViewDataComingSoon(dataListcomingsoon: List<Item>) {
        val comingsoonfilm  = ArrayList<gst.trainingcourse.movie_home.entity.comingsoon.Item>()
        adapter_comingsoon.setData(dataListcomingsoon)
        home_recyclerview_intheater.adapter?.notifyDataSetChanged()


    }

    override fun updateViewDatapopular(datalistmostpopular: List<gst.trainingcourse.movie_home.entity.MostPopular.Item>) {
        val popularfilm  = ArrayList<gst.trainingcourse.movie_home.entity.MostPopular.Item>()
        for (i in 0 .. 9)
        {
           // popularfilm.add(datalistmostpopular[i])
        }
        adapter_popular.setData(datalistmostpopular)
        home_recyclerview_popular.adapter?.notifyDataSetChanged()
    }

    override fun updateViewDataIntheater(datalistIntheater: List<gst.trainingcourse.movie_home.entity.intheater.Item>) {

        val adapter_intheater = ViewflipperAdapter(this)
        adapter_intheater.setData(datalistIntheater)
        home_viewflipper.adapter = adapter_intheater
        home_viewflipper?.isAutoStart = true
        home_viewflipper?.flipInterval = 3000
        home_viewflipper?.startFlipping()
    }


    fun viewpopular()
    {
        adapter_topmovie = MovieAdapter(this)
        home_recyclerview_topmovie?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        home_recyclerview_topmovie.adapter = adapter_topmovie
    }
    fun viewTopMovie()
    {
        adapter_popular = HomePopularAdapter(this)
        home_recyclerview_popular?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        home_recyclerview_popular.adapter = adapter_popular
    }
    fun viewComingSoon()
    {
        adapter_comingsoon = HomeComingSoonAdapter(this)
        home_recyclerview_intheater?.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.HORIZONTAL,false)
        home_recyclerview_intheater.adapter = adapter_comingsoon
        progressbar_home.visibility = View.GONE
    }

    override fun OnClickItem_Topmovie(film: Film) {
        val intent = Intent(activity,DetailActivity::class.java)
        intent.putExtra("id_movie",film.id)
        intent.putExtra("username",username)
        startActivity(intent)
    }

    override fun OnClickItem_MostPopularMovie(item: gst.trainingcourse.movie_home.entity.MostPopular.Item) {
        val intent = Intent(context,DetailActivity::class.java)
        intent.putExtra("id_movie",item.id.toString())
        intent.putExtra("username",username)
        startActivity(intent)
    }

    override fun OnClickItem_ComingSoonMovie(item: Item) {
        val intent = Intent(context,DetailActivity::class.java)
        intent.putExtra("id_movie",item.id.toString())
        intent.putExtra("username",username)
        startActivity(intent)
    }
    override fun OnClickItem_IntheaterMovie(item: gst.trainingcourse.movie_home.entity.intheater.Item) {
        val intent = Intent(context,DetailActivity::class.java)
        intent.putExtra("id_movie",item.id.toString())
        intent.putExtra("username",username)
        startActivity(intent)
    }

}