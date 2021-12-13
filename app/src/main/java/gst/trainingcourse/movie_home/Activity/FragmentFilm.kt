package gst.trainingcourse.movie_home.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.adapter.adapter_pageView
import gst.trainingcourse.movie_home.film.*
import kotlinx.android.synthetic.main.activity_film.*

class FragmentFilm : Fragment() {

    private var username :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            username = it?.getString("username")
        }
    }

    companion object {
        fun newInstance(username : String) = FragmentFilm().apply {
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
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterPageview = activity?.supportFragmentManager?.let { adapter_pageView(it) }
        adapterPageview?.addFragment(FragmentPopular.newInstance(username!!),"Popular Movie")
        adapterPageview?.addFragment(FragmentNowPlaying.newInstance(username!!),"Now Playing Movie")
        adapterPageview?.addFragment(FragmentTopRated.newInstance(username!!), "Top Rated")
        adapterPageview?.addFragment(FragmentUpcoming.newInstance(username!!), "Up Comming")
        viewpager?.adapter = adapterPageview
        tablayout?.setupWithViewPager(viewpager)
    }

}