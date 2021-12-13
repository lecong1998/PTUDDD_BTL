package gst.trainingcourse.movie_home.repository

import android.content.Context
import android.util.Log
import gst.trainingcourse.movie_home.DetailFilm.DetailFilmContract
import gst.trainingcourse.movie_home.DetailFilm.History_filmDatabase
import gst.trainingcourse.movie_home.DetailFilm.MovieActionDatabase
import gst.trainingcourse.movie_home.DetailFilm.content_trailer.YoutubeAPIContract
import gst.trainingcourse.movie_home.Profile.Profile_Contract
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.Search.SearchContract
import gst.trainingcourse.movie_home.api.APIClient
import gst.trainingcourse.movie_home.entity.History.history_film
import gst.trainingcourse.movie_home.entity.MostPopular.popular
import gst.trainingcourse.movie_home.entity.MostPopularTV.popularTv
import gst.trainingcourse.movie_home.entity.Search.Search
import gst.trainingcourse.movie_home.entity.TopMovie.Film
import gst.trainingcourse.movie_home.entity.TopMovie.FilmResponse
import gst.trainingcourse.movie_home.entity.TopTV.topTv
import gst.trainingcourse.movie_home.entity.VideoTrailer.VideoTrailer
import gst.trainingcourse.movie_home.entity.comingsoon.Item
import gst.trainingcourse.movie_home.entity.comingsoon.comingsoon
import gst.trainingcourse.movie_home.entity.detail.moviedetail
import gst.trainingcourse.movie_home.entity.intheater.intheater
import gst.trainingcourse.movie_home.entity.movie_action.movie_action
import gst.trainingcourse.movie_home.home.HomeContract
import gst.trainingcourse.movie_home.tv.TVContract
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MovieRepository(val context : Context) {

    /*--------------------------------Lịch sử xem------------------------*/
    private val historyFilmdao = History_filmDatabase.getDataBase(context).history_filmDao()

    fun getHistoryFilm( username : String, presenter : Profile_Contract.Presenter)
    {
        presenter.getdatafilm(historyFilmdao.getFilmWatched(username))
    }
    fun setHistoryFilm(historyFilm: history_film)
    {
        historyFilmdao.newHistory(historyFilm)
    }

    /*--------------------------------favorite, rate-------------------------------------*/

    private val movieactiondao = MovieActionDatabase.getDataBase(context).movie_actionDao()

    fun getMovieaction(username: String, id_movie: String, presenter: DetailFilmContract.Presenter)
    {
        presenter.UpdateDataUIMovieAction(movieactiondao.getMovieAction(username, id_movie))
    }
    fun setInsertMovieAction(movieAction: movie_action)
    {
        movieactiondao.insertMovieAction(movieAction)
    }
    fun updateMovieAction(movieAction: movie_action)
    {
        movieactiondao.updateMovieAction(movieAction)
    }

    /*---------Top movie --------*/
    fun callMostPopularMovie(presenter: HomeContract.Presenter) {
        var dataList: List<Film> = ArrayList<Film>()

        val call: Call<FilmResponse> =
            APIClient.getClient.getMostPopularMovies(context.getString(R.string.api_key))
        call.enqueue(object : Callback<FilmResponse> {
            override fun onResponse(
                call: Call<FilmResponse>?,
                response: Response<FilmResponse>?
            ) {
                if (response?.isSuccessful!!) {
                    dataList = (response?.body()?.FilmList!!)
                    presenter.updateMostPopularMovie(dataList)
                }
            }

            override fun onFailure(call: Call<FilmResponse>?, t: Throwable?) {
                Log.d("Test_API", "onFailure() called with: call = $call, t = $t")
            }
        })
    }


    /*--------------Coming Soon--------------*/
    fun callComingSoonMovie(presenter: HomeContract.Presenter)
    {
        var datalist : List<Item> = ArrayList<Item>()
        val call : Call<comingsoon> = APIClient.getClient.getComingSoon(context.getString(R.string.api_key))

        call.enqueue(object  : Callback<comingsoon>{
            override fun onResponse(call: Call<comingsoon>, response: Response<comingsoon>) {
                if(response.isSuccessful)
                {
                    datalist = (response.body()?.items!!)
                    presenter.updateComingSoonmovie(datalist)
                }
            }

            override fun onFailure(call: Call<comingsoon>, t: Throwable) {
                Log.d("Test_API", "onFailure() called with: call = $call, t = $t")
            }
        })
    }

    /*------------Most Popular-----------*/
    fun callpopularMovie(presenter: HomeContract.Presenter)
    {
        var datalistpopular : List<gst.trainingcourse.movie_home.entity.MostPopular.Item> = ArrayList<gst.trainingcourse.movie_home.entity.MostPopular.Item>()
        val call : Call<popular> = APIClient.getClient.getMostPopular(context.getString(R.string.api_key))

        call.enqueue(object : Callback<popular>{
            override fun onResponse(call: Call<popular>, response: Response<popular>) {
                if (response.isSuccessful)
                {
                    datalistpopular = response.body()?.items!!
                    presenter.updatepopularmovie(datalistpopular)
                }
            }

            override fun onFailure(call: Call<popular>, t: Throwable) {
                Log.d("Test_API", "onFailure() called with: call = $call, t = $t")
            }
        })
    }

    /*---------------Intheater------------------------*/

    fun callIntheaterMovie(presenter: HomeContract.Presenter)
    {
        var datalistintheater : List<gst.trainingcourse.movie_home.entity.intheater.Item> = ArrayList<gst.trainingcourse.movie_home.entity.intheater.Item>()
        val call : Call<intheater> = APIClient.getClient.getintheater(context.getString(R.string.api_key))
        call.enqueue(object : Callback<intheater>{
            override fun onResponse(call: Call<intheater>, response: Response<intheater>) {
                if (response.isSuccessful)
                {
                    datalistintheater = response.body()?.items!!
                    presenter.updateIntheaterMovie(datalistintheater)
                }
            }

            override fun onFailure(call: Call<intheater>, t: Throwable) {
                Log.d("Test_API", "onFailure() called with: call = $call, t = $t")
            }
        })
    }

    /*----------------TOP TV----------------------*/
    fun callTopTV(presenter: TVContract.Presenter)
    {
        var datalist : List<gst.trainingcourse.movie_home.entity.TopTV.Item> = ArrayList<gst.trainingcourse.movie_home.entity.TopTV.Item>()
        val call : Call<topTv> = APIClient.getClient.getTopTV(context.getString(R.string.api_key))
        call.enqueue(object : Callback<topTv>{
            override fun onResponse(call: Call<topTv>, response: Response<topTv>) {
                if (response.isSuccessful)
                {
                    datalist = response.body()?.items!!
                    presenter.updateUITopTV(datalist)
                    Log.d("Test_API","Thành Công")
                }
            }

            override fun onFailure(call: Call<topTv>, t: Throwable) {
                Log.d("Test_API","Thất bại")
            }
        })
    }
    /*-----------------Popular TV -------------*/
    fun callPopularTV(presenter: TVContract.Presenter)
    {
        var datalist : List<gst.trainingcourse.movie_home.entity.MostPopularTV.Item> = ArrayList<gst.trainingcourse.movie_home.entity.MostPopularTV.Item>()
        val call : Call<popularTv> = APIClient.getClient.getMostPopularTVs(context.getString(R.string.api_key))
        call.enqueue(object : Callback<popularTv>{
            override fun onResponse(call: Call<popularTv>, response: Response<popularTv>) {
                if(response.isSuccessful)
                {
                    datalist = response.body()?.items!!
                    presenter.updateUIPopularTV(datalist)
                    Log.d("Test_API","Thành Công")

                }
            }

            override fun onFailure(call: Call<popularTv>, t: Throwable) {
                Log.d("Test_API","Thất bại")
            }
        })
    }

    /*-----------------Detail FIlM------------------------*/

    fun callDetailFilm(presenter: DetailFilmContract.Presenter,id_movie: String)
    {
        var detail_Film : moviedetail? = null
        val call : Call<moviedetail> = APIClient.getClient.getDetailMovie(context.getString(R.string.api_key),id_movie)
        call.enqueue(object : Callback<moviedetail>{
            override fun onResponse(call: Call<moviedetail>, response: Response<moviedetail>) {
                if (response.isSuccessful)
                {
                    detail_Film = response.body()!!
                    presenter.UpdateDataUI(detail_Film!!)
                }
            }

            override fun onFailure(call: Call<moviedetail>, t: Throwable) {
                Log.d("Test_API_Detail","Lỗi RỒi")
            }
        })
    }

    /*---------------------------------------------------------*/
    fun callVideoTrailer(presenter: YoutubeAPIContract.Presenter, movieid: String) {
        val call: Call<VideoTrailer> =
            APIClient.getClient.getVideoTrailer(context.getString(R.string.api_key), movieid)
        call.enqueue(object : Callback<VideoTrailer> {
            override fun onResponse(
                call: Call<VideoTrailer>?,
                response: Response<VideoTrailer>?
            ) {
                val m = response!!.body()!!
                presenter.updateVideoTrailer(m.videoId)
            }

            override fun onFailure(call: Call<VideoTrailer>?, t: Throwable?) {
                Log.d("Test_API", "onFailure() called with: call = $call, t = $t")
            }
        })
    }

    /*-------------------------------------------------------*/
    fun callSearchTitle(presenter: SearchContract.Presenter,title: String)
    {
        var data : Search?  = null
        val call : Call<Search> = APIClient.getClient.getTypeTitle(context.getString(R.string.api_key),title)
        call.enqueue(object : Callback<Search>{
            override fun onResponse(call: Call<Search>, response: Response<Search>) {
                if (response.isSuccessful)
                {
                    data = response.body()!!
                    presenter.updateUISearch(data!!)
                }
            }

            override fun onFailure(call: Call<Search>, t: Throwable) {
                Log.e("Test","Thất bại")
            }
        })
    }

}