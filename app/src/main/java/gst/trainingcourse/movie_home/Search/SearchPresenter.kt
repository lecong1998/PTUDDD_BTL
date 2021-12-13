package gst.trainingcourse.movie_home.Search

import gst.trainingcourse.movie_home.entity.Search.Search
import gst.trainingcourse.movie_home.repository.MovieRepository

class SearchPresenter(
    private val View : SearchContract.View,
    private val repository: MovieRepository
) : SearchContract.Presenter{
    override fun getSearchTitle(title: String) {
        repository.callSearchTitle(this,title)
    }

    override fun updateUISearch(search: Search) {
        View.updateViewSearch(search)
    }
}