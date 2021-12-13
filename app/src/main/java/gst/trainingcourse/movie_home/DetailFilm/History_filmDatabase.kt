package gst.trainingcourse.movie_home.DetailFilm

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gst.trainingcourse.movie_home.SignIn.SignInDatabase
import gst.trainingcourse.movie_home.entity.History.history_film
import gst.trainingcourse.movie_home.entity.History.history_filmDao

@Database(entities = [history_film::class],version = 3)
abstract class History_filmDatabase : RoomDatabase() {
    abstract fun history_filmDao() : history_filmDao

    companion object {
        @Volatile

        private var INSTANCE : History_filmDatabase? = null

        fun getDataBase(context: Context): History_filmDatabase{
            val instance = INSTANCE
            if (instance != null)
            {
                return instance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    History_filmDatabase::class.java,
                    "Database_history"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return  instance
            }
        }
    }
}