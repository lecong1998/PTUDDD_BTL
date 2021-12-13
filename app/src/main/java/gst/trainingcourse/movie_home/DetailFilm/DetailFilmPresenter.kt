package gst.trainingcourse.movie_home.DetailFilm

import gst.trainingcourse.movie_home.entity.detail.moviedetail
import gst.trainingcourse.movie_home.entity.movie_action.movie_action
import gst.trainingcourse.movie_home.repository.MovieRepository

class DetailFilmPresenter(
    private val View : DetailFilmContract.View,
    private val repository: MovieRepository
): DetailFilmContract.Presenter {
    override fun getDetailFilm(id_movie: String) {
        repository.callDetailFilm(this,id_movie)
    }

    override fun UpdateDataUI(moviedetail: moviedetail) {
        View.UpdateDataViewDetailFilm(moviedetail)
    }

    override fun getMovieAction(username: String,id_movie: String) {
        repository.getMovieaction(username,id_movie,this)
    }

    override fun UpdateDataUIMovieAction(movieAction: movie_action?) {
        if (movieAction != null) {
            View.UpdateDataViewMovieAction(movieAction)
        }
    }

    override fun setInsertMovieAction(movieAction: movie_action) {
        repository.setInsertMovieAction(movieAction)
    }



    override fun setUpdateMovieAction(movieAction: movie_action) {
        repository.updateMovieAction(movieAction)
    }


}