package gst.trainingcourse.movie_home.entity.comment

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "comment")
data class comment (var username : String,var id_movie : String,var comment : String,var image : String?, var datetime : String )
{
    @PrimaryKey(autoGenerate = true)
    var id : Int = 0
}
