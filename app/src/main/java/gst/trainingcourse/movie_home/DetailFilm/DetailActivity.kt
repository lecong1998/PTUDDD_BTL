package gst.trainingcourse.movie_home.DetailFilm

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import gst.trainingcourse.movie_home.DetailFilm.content_trailer.ContentFilmActivity
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.SignIn.SignInDatabase
import gst.trainingcourse.movie_home.entity.History.history_film
import gst.trainingcourse.movie_home.entity.detail.moviedetail
import gst.trainingcourse.movie_home.entity.movie_action.movie_action
import gst.trainingcourse.movie_home.repository.MovieRepository
import kotlinx.android.synthetic.main.fragment_detail.*
import kotlinx.android.synthetic.main.ratingdialog.view.*

class DetailActivity: AppCompatActivity(), DetailFilmContract.View {

    private lateinit var presenter: DetailFilmContract.Presenter
    private  var moviedetail: moviedetail? = null

    private var id_movie : String? = null
    private var username : String? = null

    private var movieAction : movie_action? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_detail)

        id_movie = intent.getStringExtra("id_movie")
        username = intent.getStringExtra("username")



       // Log.e("idmovie",id_movie.toString())
       // Toast.makeText(this,username.toString(),Toast.LENGTH_SHORT).show()

        presenter = DetailFilmPresenter(this, MovieRepository(applicationContext))
        id_movie?.let { presenter.getDetailFilm(it) }
      //  presenter.getMovieAction(username!!,id_movie!!)


        movieAction = MovieActionDatabase.getDataBase(applicationContext).movie_actionDao().getMovieAction(username!!,id_movie!!)
        if (movieAction == null)
        {
            movieAction = movie_action(username,id_movie,false,0F,false)
            MovieActionDatabase.getDataBase(applicationContext).movie_actionDao().insertMovieAction(movieAction!!)
        }
        if (movieAction?.favorite == false)
        {
            detailfilm_btnfavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
        }
        else
        {
            detailfilm_btnfavorite.setImageResource(R.drawable.ic_favorite)
        }




        /*-------------------------favorite----------------------------*/


        detailfilm_btnfavorite.setOnClickListener {
            movieAction = MovieActionDatabase.getDataBase(applicationContext).movie_actionDao().getMovieAction(username!!,id_movie!!)
            if (movieAction?.favorite == false)
            {
                detailfilm_btnfavorite.setImageResource(R.drawable.ic_baseline_favorite_24)
                movieAction = MovieActionDatabase.getDataBase(applicationContext).movie_actionDao().getMovieAction(username!!,id_movie!!)
                movieAction?.favorite = true
                MovieActionDatabase.getDataBase(applicationContext).movie_actionDao().updateMovieAction(movieAction!!)
                Toast.makeText(applicationContext,"Đã thêm vào favorit!",Toast.LENGTH_LONG).show()
            }
            else
            {
                detailfilm_btnfavorite.setImageResource(R.drawable.ic_favorite)
                movieAction = MovieActionDatabase.getDataBase(applicationContext).movie_actionDao().getMovieAction(username!!,id_movie!!)
                movieAction?.favorite = false
                MovieActionDatabase.getDataBase(applicationContext).movie_actionDao().updateMovieAction(movieAction!!)
                Toast.makeText(applicationContext,"Đã loại bỏ khỏi favorite!",Toast.LENGTH_SHORT).show()
            }

        }

        /*-----------------------------ratebar-----------------------------*/

        detaildfilm_rate.setOnClickListener {
            movieAction = MovieActionDatabase.getDataBase(applicationContext).movie_actionDao().getMovieAction(username!!,id_movie!!)
            val dialogBuilder = AlertDialog.Builder(this)
            val view = LayoutInflater.from(this).inflate(R.layout.ratingdialog,null)
            dialogBuilder.setView(view)
            val dialog = dialogBuilder.create()
            dialog.show()
            view.ratebar_detail.rating = movieAction!!.rate

            view.detail_submit.setOnClickListener {
                    movieAction = MovieActionDatabase.getDataBase(applicationContext).movie_actionDao().getMovieAction(username!!,id_movie!!)
                    movieAction?.rate = view.ratebar_detail.rating
                    Toast.makeText(this,"Rating thành công!",Toast.LENGTH_LONG).show()
                    dialog.dismiss()
                    MovieActionDatabase.getDataBase(applicationContext).movie_actionDao().updateMovieAction(movieAction!!)
            }
        }




/*------------------------------xem trailer--------------------------------*/
        buttom_playtrailer.setOnClickListener {

            val intent = Intent(this,ContentFilmActivity::class.java)
            intent.putExtra("id_movie",id_movie)
            intent.putExtra("username",username)
            startActivity(intent)

        }
    }

    override fun UpdateDataViewDetailFilm(moviedetail: moviedetail) {
        //this.moviedetail = moviedetail
        Picasso.get().load(moviedetail!!.image).error(R.drawable.anh3).into(anhnenphimdetail)
        Picasso.get().load(moviedetail!!.image).error(R.drawable.anh3).into(anhphimdetail)
        tenphim_detail.text = moviedetail!!.title.toString()
        year_detail.text = moviedetail!!.year.toString()
       // ratingdetail.text = moviedetail!!.imDbRating.toString()
        overview_content.text = moviedetail!!.plot.toString()

        recyclerview_series_cast.layoutManager = LinearLayoutManager(applicationContext,LinearLayoutManager.HORIZONTAL,false)

        recyclerview_series_cast.adapter = adapter_actor(moviedetail.actorList)

        detail_progressbar.visibility = View.GONE


    }

    override fun UpdateDataViewMovieAction(movieAction: movie_action?) {
        this.movieAction = movieAction
    }

}