package gst.trainingcourse.movie_home.Profile

import gst.trainingcourse.movie_home.SignIn.UserRepository
import gst.trainingcourse.movie_home.entity.History.history_film
import gst.trainingcourse.movie_home.entity.User.user
import gst.trainingcourse.movie_home.repository.MovieRepository

class Profile_Presenter(
    private val View : Profile_Contract.View,
    private val movieRepository: MovieRepository
) : Profile_Contract.Presenter{
    override fun getUIFilm(username: String)  {
       movieRepository.getHistoryFilm(username,this)
    }

    override fun getdatafilm(listfilm: List<history_film>) {
        View.getViewDataFilmWatched(listfilm)
    }
}