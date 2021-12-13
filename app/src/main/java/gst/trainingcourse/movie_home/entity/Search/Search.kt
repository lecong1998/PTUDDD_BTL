package gst.trainingcourse.movie_home.entity.Search

data class Search(
    val errorMessage: String,
    val expression: String,
    val results: List<Result>,
    val searchType: String
)