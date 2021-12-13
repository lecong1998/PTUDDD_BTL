package gst.trainingcourse.movie_home.DetailFilm.content_trailer

import gst.trainingcourse.movie_home.entity.VideoTrailer.VideoTrailer

interface YoutubeAPIContract {
    interface View{
        fun updateViewData(Video_id: String)
    }

    interface Presenter{
        fun getVideoTrailer(movieId:String)
        fun updateVideoTrailer(video_id: String)
        fun updateaddmoviehistory(username : String,movieId: String)
    }
}