package gst.trainingcourse.movie_home.entity.movie_action

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface movie_actionDao {
    @Insert(onConflict = REPLACE)
    fun insertMovieAction(movieAction: movie_action)

    @Query("SELECT * FROM action_film WHERE username = :username AND id_movie = :id_movie")
    fun getMovieAction(username : String, id_movie : String) : movie_action?

    @Update(onConflict = REPLACE)
    fun updateMovieAction(movieAction: movie_action)

}