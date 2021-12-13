package gst.trainingcourse.movie_home.entity.comment

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query

@Dao
interface commentDao {
    @Insert(onConflict = REPLACE)
    fun setCommentUsername(comment: comment)

    @Query("SELECT * FROM comment WHERE id_movie = :id_movie")
    fun getCommentMovie(id_movie: String) : List<comment>
}