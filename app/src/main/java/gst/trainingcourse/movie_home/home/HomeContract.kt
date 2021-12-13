package gst.trainingcourse.movie_home.home

import gst.trainingcourse.movie_home.entity.TopMovie.Film
import gst.trainingcourse.movie_home.entity.comingsoon.Item

interface HomeContract {

    interface View{
        fun updateViewDataMostPopularMovie(dataList: List<Film>)
        fun updateViewDataIntheater(datalistIntheater : List<gst.trainingcourse.movie_home.entity.intheater.Item>)
        fun updateViewDatapopular(datalistmostpopular : List<gst.trainingcourse.movie_home.entity.MostPopular.Item>)
        fun updateViewDataComingSoon(dataListcomingsoon: List<gst.trainingcourse.movie_home.entity.comingsoon.Item>)
    }

    interface Presenter{
        /*----------TopMovie-------------*/
        fun getMostPopularMovie()
        fun updateMostPopularMovie(dataList: List<Film>)
        /*--------------Intheater-----------------*/
        fun getIntheaterMovie()
        fun updateIntheaterMovie(dataListIntheater: List<gst.trainingcourse.movie_home.entity.intheater.Item>)
        /*-------------Most Popular----------------*/

        fun getpopularmovie()
        fun updatepopularmovie(datalistmostpopular: List<gst.trainingcourse.movie_home.entity.MostPopular.Item>)
        /*--------------ComingSoon-------------------*/
        fun getComingSoonmovie()
        fun updateComingSoonmovie(datalistComingSoon: List<gst.trainingcourse.movie_home.entity.comingsoon.Item>)
    }
}