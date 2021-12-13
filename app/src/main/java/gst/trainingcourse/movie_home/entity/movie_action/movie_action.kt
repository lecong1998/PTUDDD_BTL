package gst.trainingcourse.movie_home.entity.movie_action

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "action_film")
data class movie_action(var username : String?, var id_movie : String?, var favorite: Boolean = false, var rate : Float = 0F, var tick : Boolean =false)  {
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}