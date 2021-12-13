package gst.trainingcourse.movie_home.tv

import gst.trainingcourse.movie_home.entity.TopTV.Item
import gst.trainingcourse.movie_home.repository.MovieRepository

class TVPresenter(
    private val View : TVContract.View,
    private val repository: MovieRepository
) : TVContract.Presenter{
    override fun getDataTopTV() {
        repository.callTopTV(this)
    }

    override fun updateUITopTV(datalisttoptv: List<Item>) {
        View.updateDataTopTV(datalisttoptv)
    }

    override fun getDataPopularTV() {
        repository.callPopularTV(this)
    }

    override fun updateUIPopularTV(datalistpopulartv: List<gst.trainingcourse.movie_home.entity.MostPopularTV.Item>) {
        View.updateDataPopularTV(datalistpopulartv)
    }
}