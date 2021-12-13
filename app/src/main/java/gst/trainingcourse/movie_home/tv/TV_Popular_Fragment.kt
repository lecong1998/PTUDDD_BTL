package gst.trainingcourse.movie_home.tv

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import gst.trainingcourse.movie_home.DetailFilm.DetailActivity
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.entity.TopTV.Item
import gst.trainingcourse.movie_home.repository.MovieRepository
import kotlinx.android.synthetic.main.fragment_t_v__popular_.*


class TV_Popular_Fragment : Fragment() , TVContract.View,OnClickItem_TVPopular{

    private lateinit var presenter : TVContract.Presenter
    private lateinit var adapter_populartv : TVPopularAdapter

    private var username :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            username = it?.getString("username")
        }
    }

    companion object {
        fun newInstance(username : String) = TV_Popular_Fragment().apply {
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
        return inflater.inflate(R.layout.fragment_t_v__popular_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = TVPresenter(this, MovieRepository(requireContext()))
        adapter_populartv = TVPopularAdapter(this)
        presenter.getDataPopularTV()


        tv_recycleview_popular?.layoutManager = GridLayoutManager(activity,2, GridLayoutManager.VERTICAL,false)
        tv_recycleview_popular.adapter = adapter_populartv

    }

    override fun updateDataTopTV(datalisttoptv: List<Item>) {
        TODO("Not yet implemented")
    }

    override fun updateDataPopularTV(datalistpopulartv: List<gst.trainingcourse.movie_home.entity.MostPopularTV.Item>) {
        adapter_populartv.setData(datalistpopulartv)
        tv_recycleview_popular.adapter?.notifyDataSetChanged()
        tv_popular_progressbar.visibility = View.GONE
    }

    override fun OnClickItem_PopularTV(item: gst.trainingcourse.movie_home.entity.MostPopularTV.Item) {
        val intent = Intent(activity,DetailActivity::class.java)
        intent.putExtra("id_movie",item.id.toString())
        intent.putExtra("username",username)
        startActivity(intent)
    }

}