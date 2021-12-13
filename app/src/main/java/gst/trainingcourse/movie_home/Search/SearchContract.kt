package gst.trainingcourse.movie_home.Search

import gst.trainingcourse.movie_home.entity.Search.Search

interface SearchContract {

    interface View {
        fun updateViewSearch(search: Search)
    }
    interface Presenter{
        fun getSearchTitle(title: String)
        fun updateUISearch(search: Search)
    }

}