package gst.trainingcourse.movie_home.entity.VideoTrailer

data class VideoTrailer(
    val errorMessage: String,
    val fullTitle: String,
    val imDbId: String,
    val title: String,
    val type: String,
    val videoId: String,
    val videoUrl: String,
    val year: String
)