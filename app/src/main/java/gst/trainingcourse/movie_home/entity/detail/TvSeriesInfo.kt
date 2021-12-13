package gst.trainingcourse.movie_home.entity.detail

data class TvSeriesInfo(
    val creatorList: List<Creator>,
    val creators: String,
    val seasons: List<String>,
    val yearEnd: String
)