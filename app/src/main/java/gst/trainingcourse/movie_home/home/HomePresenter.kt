package gst.trainingcourse.movie_home.home

import gst.trainingcourse.movie_home.entity.TopMovie.Film
import gst.trainingcourse.movie_home.entity.comingsoon.Item
import gst.trainingcourse.movie_home.repository.MovieRepository

class HomePresenter(
    private val view: HomeContract.View,
    private val repository: MovieRepository
) : HomeContract.Presenter {
    override fun getMostPopularMovie() {
        repository.callMostPopularMovie(this)
    }

    override fun updateMostPopularMovie(dataList: List<Film>) {
        view.updateViewDataMostPopularMovie(dataList)
    }

    override fun getIntheaterMovie() {
        repository.callIntheaterMovie(this)
    }

    override fun updateIntheaterMovie(dataListIntheater: List<gst.trainingcourse.movie_home.entity.intheater.Item>) {
        view.updateViewDataIntheater(dataListIntheater)
    }


    override fun getpopularmovie() {
        repository.callpopularMovie(this)
    }

    override fun updatepopularmovie(datalistmostpopular: List<gst.trainingcourse.movie_home.entity.MostPopular.Item>) {
        view.updateViewDatapopular(datalistmostpopular)
    }

    override fun getComingSoonmovie() {
        repository.callComingSoonMovie(this)
    }

    override fun updateComingSoonmovie(datalistComingSoon: List<Item>) {
        view.updateViewDataComingSoon(datalistComingSoon)
    }


}