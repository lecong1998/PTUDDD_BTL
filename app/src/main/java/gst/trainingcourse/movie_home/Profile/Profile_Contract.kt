package gst.trainingcourse.movie_home.Profile

import gst.trainingcourse.movie_home.entity.History.history_film
import gst.trainingcourse.movie_home.entity.User.user

interface Profile_Contract {
    interface View{
        fun getViewDataFilmWatched(listfilm : List<history_film>)
    }
    interface Presenter{

        fun getUIFilm(username: String)

        fun getdatafilm(listfilm: List<history_film>)
    }
}