package gst.trainingcourse.movie_home.entity.History

import androidx.room.Entity
import androidx.room.PrimaryKey
@Entity(tableName = "history_film")
data class history_film( var username : String,var id_movie : String)  {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}