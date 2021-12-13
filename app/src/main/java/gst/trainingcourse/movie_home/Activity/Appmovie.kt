package gst.trainingcourse.movie_home.Activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.viewpager.widget.ViewPager
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.adapter.adapter_pageView
import kotlinx.android.synthetic.main.activity_appmovie.*

class Appmovie : AppCompatActivity() {

    var adapter : adapter_pageView? = null

    var username : String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_appmovie)

        setSupportActionBar(home_toolbar)
        val home_appbar = supportActionBar

        username = intent.getStringExtra("username")
        Log.d("username",username.toString())
        Toast.makeText(this,username,Toast.LENGTH_LONG).show()
        adapter = adapter_pageView(supportFragmentManager)
        adapter!!.addFragment(FragmentHome.newInstance(username!!),"Home")
        adapter!!.addFragment(FragmentFilm.newInstance(username!!),"Film")
        adapter!!.addFragment(FragmentTV.newInstance(username!!),"TV")
        username?.let { FragmentProfile.newInstance(it) }?.let { adapter!!.addFragment(it,"Profile") }
        viewpager_activity?.adapter = adapter

        buttom_navigation?.setOnNavigationItemSelectedListener {
            when(it.itemId)
            {
                R.id.home ->{
                    viewpager_activity.currentItem=0
                    home_toolbar.title = "HOME"
                }
                R.id.film ->{
                    viewpager_activity.currentItem=1
                    home_toolbar.title = "FILM"
                }
                R.id.tv ->{
                    viewpager_activity.currentItem=2
                    home_toolbar.title = "TV"
                }
                R.id.profile ->{
                    viewpager_activity.currentItem=3
                    home_toolbar.title = "PROFILE"
                }
            }
            true
        }

        viewpager_activity.addOnPageChangeListener(object : ViewPager.OnPageChangeListener{
            override fun onPageScrollStateChanged(state: Int) {
            }

            override fun onPageScrolled(
                position: Int,
                positionOffset: Float,
                positionOffsetPixels: Int
            ) {
            }

            override fun onPageSelected(position: Int) {
                when(position){
                    0 -> {
                        buttom_navigation.menu.findItem(R.id.home).setChecked(true)
                    }
                    1 -> {
                        buttom_navigation.menu.findItem(R.id.film).setChecked(true)
                    }
                    2 -> {
                        buttom_navigation.menu.findItem(R.id.tv).setChecked(true)
                    }
                    3 -> {
                        buttom_navigation.menu.findItem(R.id.profile).setChecked(true)
                    }
                }
            }
        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when(item.itemId)
        {
            R.id.menu_search -> {
                fragmentsearch()
            }
        }

        return super.onOptionsItemSelected(item)
    }

    fun fragmentsearch()
    {
        home_toolbar.title = "Search"
        supportFragmentManager.beginTransaction()
            .add(R.id.framelayout_movie,FragmentSearch(),FragmentSearch::class.java.name)
            .addToBackStack(FragmentSearch::class.java.name)
            .commit()
    }


}