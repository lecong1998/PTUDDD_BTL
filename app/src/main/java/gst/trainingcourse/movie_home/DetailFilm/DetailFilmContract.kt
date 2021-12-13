package gst.trainingcourse.movie_home.DetailFilm

import gst.trainingcourse.movie_home.entity.detail.moviedetail
import gst.trainingcourse.movie_home.entity.movie_action.movie_action

interface DetailFilmContract {
    interface View{
        fun UpdateDataViewDetailFilm(moviedetail: moviedetail)

        /*---------------------lấy thông tin favorite,rate---------------------------*/
        fun UpdateDataViewMovieAction(movieAction: movie_action?)
        /*----------------------------insert favorit,rate----------------------------------*/
       // fun UpdateDataViewInsertMovieAction(movieAction: movie_action)
        /*--------------------------update favorit,rate-------------------------------------*/
       // fun UpdateDataViewUpdateMovieAction(movieAction: movie_action)

    }
    interface Presenter{
        fun getDetailFilm(id_movie: String)
        fun UpdateDataUI(moviedetail: moviedetail)

        /*------------------------------*/
        fun getMovieAction(username : String, id_movie: String)
        fun UpdateDataUIMovieAction(movieAction: movie_action?)
        /*-------------------------*/
        fun setInsertMovieAction(movieAction: movie_action)
        /*-----------setUpdateMovieAction--------------*/
        fun setUpdateMovieAction(movieAction: movie_action)


    }
}