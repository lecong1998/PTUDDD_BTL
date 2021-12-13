package gst.trainingcourse.movie_home.Activity

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.adapter.adapter_pageView
import gst.trainingcourse.movie_home.tv.TV_Popular_Fragment
import gst.trainingcourse.movie_home.tv.TV_TopRated_Fragment
import kotlinx.android.synthetic.main.activity_tv.*

class FragmentTV : Fragment() {

    private var username :String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            username = it?.getString("username")
        }
    }

    companion object {
        fun newInstance(username : String) = FragmentTV().apply {
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
        return inflater.inflate(R.layout.fragment_t_v, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val adapterPageview = activity?.supportFragmentManager?.let { adapter_pageView(it) }
        adapterPageview?.addFragment(TV_Popular_Fragment.newInstance(username!!),"Popular TV")
        adapterPageview?.addFragment(TV_TopRated_Fragment.newInstance(username!!), "Top Rated")
        viewpager_tv?.adapter = adapterPageview
        tablayout_tv?.setupWithViewPager(viewpager_tv)
    }

}