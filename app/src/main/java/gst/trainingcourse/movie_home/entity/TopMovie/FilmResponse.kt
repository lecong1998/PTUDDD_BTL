package gst.trainingcourse.movie_home.entity.TopMovie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import gst.trainingcourse.movie_home.entity.TopMovie.Film

class FilmResponse (
    @Expose
    @SerializedName("items")
    val FilmList: List<Film>? = null
)