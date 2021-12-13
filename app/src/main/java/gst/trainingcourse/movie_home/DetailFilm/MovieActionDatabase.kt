package gst.trainingcourse.movie_home.DetailFilm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gst.trainingcourse.movie_home.entity.movie_action.movie_action
import gst.trainingcourse.movie_home.entity.movie_action.movie_actionDao

@Database(entities = [movie_action::class],version = 5)
abstract class MovieActionDatabase : RoomDatabase(){
    abstract fun movie_actionDao() : movie_actionDao
    companion object
    {
        @Volatile
        private var INSTANCE : MovieActionDatabase ? = null

        fun getDataBase(context: Context) : MovieActionDatabase {
            val instance = INSTANCE
            if (instance != null)
            {
                return instance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    MovieActionDatabase::class.java,
                    "Database_movie_action"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance
            }
        }
    }
}