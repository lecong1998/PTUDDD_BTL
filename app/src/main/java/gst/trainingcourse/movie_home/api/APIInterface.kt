package gst.trainingcourse.movie_home.api

import gst.trainingcourse.movie_home.entity.MostPopular.popular
import gst.trainingcourse.movie_home.entity.MostPopularTV.popularTv
import gst.trainingcourse.movie_home.entity.Search.Search
import gst.trainingcourse.movie_home.entity.TopMovie.FilmResponse
import gst.trainingcourse.movie_home.entity.TopTV.topTv
import gst.trainingcourse.movie_home.entity.VideoTrailer.VideoTrailer
import gst.trainingcourse.movie_home.entity.comingsoon.comingsoon
import gst.trainingcourse.movie_home.entity.detail.moviedetail
import gst.trainingcourse.movie_home.entity.intheater.intheater
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface APIInterface {
    /*-----------Top Movie---------*/
    @GET("Top250Movies/{api_key}")
    fun getMostPopularMovies(@Path("api_key") api_key: String): Call<FilmResponse>
    /*-----------Coming soon--------*/
    @GET("ComingSoon/{api_key}")
    fun getComingSoon(@Path("api_key") api_key: String) : Call<comingsoon>
    /*--------Most Popular---------*/
    @GET("MostPopularMovies/{api_key}")
    fun getMostPopular(@Path("api_key") api_key: String) : Call<popular>
    /*------------Intheater----------------*/
    @GET("InTheaters/{api_key}")
    fun getintheater(@Path("api_key") api_key: String) : Call<intheater>

    /*-----------Top Tv ---------*/
    @GET("Top250TVs/{api_key}")
    fun getTopTV(@Path("api_key") api_key: String) : Call<topTv>
    /*-----------popular Tv----------*/
    @GET("MostPopularTVs/{api_key}")
    fun getMostPopularTVs(@Path("api_key") api_key: String) : Call<popularTv>

    /*------------Detail Film theo Id ---------------*/
    @GET("Title/{api_key}/{id_movie}")
    fun getDetailMovie(@Path("api_key") api_key: String, @Path("id_movie") id_movie: String) : Call<moviedetail>

    /*---------------------------------*/
    @GET("YouTubeTrailer/{api_key}/{id_movie}")
    fun getVideoTrailer(@Path("api_key") api_key: String, @Path("id_movie") id_movie: String): Call<VideoTrailer>

    /*-----------------Search--------------------*/
    @GET("SearchTitle/{api_key}/{title}")
    fun getTypeTitle(@Path("api_key") api_key: String,@Path("title") title : String) : Call<Search>
}