package gst.trainingcourse.movie_home.DetailFilm.content_trailer

import gst.trainingcourse.movie_home.entity.History.history_film
import gst.trainingcourse.movie_home.entity.VideoTrailer.VideoTrailer
import gst.trainingcourse.movie_home.repository.MovieRepository


class YoutubeAPIPresenter(
    private val view: YoutubeAPIContract.View,
    private val repository: MovieRepository
): YoutubeAPIContract.Presenter {
    override fun getVideoTrailer(movieId: String) {
        repository.callVideoTrailer(this, movieId)
    }

    override fun updateVideoTrailer(video_id: String) {
        view.updateViewData(video_id)
    }

    override fun updateaddmoviehistory(username : String,movieId: String) {
        repository.setHistoryFilm(history_film(username,movieId))
    }
}