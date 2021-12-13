package gst.trainingcourse.movie_home.entity.TopMovie

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Film (
    @Expose
    @SerializedName("id")
    val id: String,
    @Expose
    @SerializedName("title")
    val title: String,
    @Expose
    @SerializedName("image")
    val image: String,
    @Expose
    @SerializedName("description")
    val description: String
    )