package gst.trainingcourse.movie_home.DetailFilm.content_trailer

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.squareup.picasso.Picasso
import gst.trainingcourse.movie_home.DetailFilm.History_filmDatabase
import gst.trainingcourse.movie_home.Profile.Profile_Contract
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.SignIn.SignInDatabase
import gst.trainingcourse.movie_home.adapter.adapter_comment
import gst.trainingcourse.movie_home.entity.History.history_film
import gst.trainingcourse.movie_home.entity.User.user
import gst.trainingcourse.movie_home.entity.VideoTrailer.VideoTrailer
import gst.trainingcourse.movie_home.entity.comment.comment
import gst.trainingcourse.movie_home.repository.MovieRepository
import kotlinx.android.synthetic.main.fragment_content_film.*
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*


class ContentFilmActivity
    : YouTubeBaseActivity(),YouTubePlayer.OnInitializedListener, YoutubeAPIContract.View {

    private lateinit var presenter: YoutubeAPIContract.Presenter

    private var VIDEO_ID: String = ""

    private var id_movie : String? = null
    private var username : String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.fragment_content_film)
        id_movie = intent.getStringExtra("id_movie")
        username = intent.getStringExtra("username")
        presenter = YoutubeAPIPresenter(this, MovieRepository(this))
        id_movie?.let { presenter.getVideoTrailer(it) }

        try {
            presenter.updateaddmoviehistory(username!!,id_movie!!)
        }catch (e : NullPointerException)
        {
            e.printStackTrace()
        }

        if (id_movie!= null)
        {
            var listcomment: List<comment> = commentDatabase.getDatabase(this).commentDao().getCommentMovie(id_movie!!)
            val adapter = adapter_comment(listcomment.reversed())
            comment_recyclerview.layoutManager = LinearLayoutManager(this)
            comment_recyclerview.adapter = adapter
        }
        dang_comment.setOnClickListener {
            if (edt_comment.text.isEmpty())
            {
                Toast.makeText(this,"Bạn chưa nhập bình luận",Toast.LENGTH_SHORT).show()

            }
            else
            {
                val user : user ?= SignInDatabase.getDatabase(this).userDao().getUserusername(username!!)

                val currentDateandTime : String = java.time.LocalDateTime.now().toString()
                val comment = comment(username!!,id_movie!!,edt_comment.text.toString(),user?.img,currentDateandTime!!)

                commentDatabase.getDatabase(this).commentDao().setCommentUsername(comment)
                Toast.makeText(this,"Bình luận thành công!",Toast.LENGTH_SHORT).show()
                edt_comment.text.clear()
                if (id_movie!= null)
                {
                    var listcomment: List<comment> = commentDatabase.getDatabase(this).commentDao().getCommentMovie(id_movie!!)
                    val adapter = adapter_comment(listcomment.reversed())
                    comment_recyclerview.layoutManager = LinearLayoutManager(this)
                    comment_recyclerview.adapter = adapter
                }
                comment_recyclerview.scrollToPosition(0)
            }
        }

    }



    override fun updateViewData(Video_id : String) {
        if(Video_id!= "")
        {
            VIDEO_ID = Video_id
        }
        else
        {
            Toast.makeText(this,"Chưa có Video",Toast.LENGTH_SHORT).show()
        }

        viewcontent!!.initialize(getString(R.string.youtube_key),this@ContentFilmActivity)
    }

    override fun onInitializationSuccess(
        p0: YouTubePlayer.Provider?,
        p1: YouTubePlayer?,
        p2: Boolean
    ) {
        if (!p2){
            p1?.setShowFullscreenButton(true)
            p1?.cueVideo(VIDEO_ID)
        }
    }

    override fun onInitializationFailure(
        p0: YouTubePlayer.Provider?,
        p1: YouTubeInitializationResult?
    ) {
        val REQUEST_CODE = 0

        if (p1?.isUserRecoverableError == true) {
            p1.getErrorDialog(this, REQUEST_CODE).show()
        } else {
            val errorMessage =
                "There was an error initializing the YoutubePlayer ($p1)"
            Toast.makeText(this, errorMessage, Toast.LENGTH_LONG).show()
        }
    }


}