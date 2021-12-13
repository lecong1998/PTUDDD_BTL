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
import kotlinx.android.synthetic.main.fragment_t_v__top_rated_.*


class TV_TopRated_Fragment : Fragment() , TVContract.View,OnClickItem_TVtop{

    private lateinit var presenter : TVContract.Presenter
    private lateinit var adapter_toptv : TVTopAdapter

    private var username :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        arguments.let {
            username = it?.getString("username")
        }
    }

    companion object {
        fun newInstance(username : String) = TV_TopRated_Fragment().apply {
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
        return inflater.inflate(R.layout.fragment_t_v__top_rated_, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = TVPresenter(this, MovieRepository(requireContext()))

        adapter_toptv = TVTopAdapter(this)
        presenter.getDataTopTV()

        tv_recycleview_toprated?.layoutManager = GridLayoutManager(activity,2, GridLayoutManager.VERTICAL,false)

        tv_recycleview_toprated.adapter = adapter_toptv

    }

    override fun updateDataTopTV(datalisttoptv: List<Item>) {
        adapter_toptv.setData(datalisttoptv)

        tv_recycleview_toprated.adapter?.notifyDataSetChanged()
        tv_topmovie_progressbar.visibility = View.GONE
    }

    override fun updateDataPopularTV(datalistpopulartv: List<gst.trainingcourse.movie_home.entity.MostPopularTV.Item>) {
        TODO("Not yet implemented")
    }

    override fun OnClickItem_TopTV(item: Item) {
        val intent = Intent(context,DetailActivity::class.java)
        intent.putExtra("id_movie",item.id.toString())
        intent.putExtra("username",username)
        startActivity(intent)
    }
}