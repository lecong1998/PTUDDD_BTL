package gst.trainingcourse.movie_home.entity.History

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface history_filmDao {
    @Insert(onConflict = REPLACE)
    fun newHistory(historyFilm: history_film)

    @Query("SELECT * FROM history_film WHERE username = :username")
    fun getFilmWatched(username : String) : List<history_film>

    @Query("SELECT * FROM history_film WHERE id_movie = :idmovie")
    fun getFilmHas(idmovie : String) : List<history_film>
}