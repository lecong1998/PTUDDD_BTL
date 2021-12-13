package gst.trainingcourse.movie_home.entity.User

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "user_table")
data class user(var username: String, var password: String, var fullname: String, var email: String) {

    @PrimaryKey(autoGenerate = true)
    var id: Int = 0
    var country: String? = ""
    var img: String? = ""

}