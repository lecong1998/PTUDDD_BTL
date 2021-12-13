package gst.trainingcourse.movie_home.DetailFilm.content_trailer

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gst.trainingcourse.movie_home.entity.comment.comment
import gst.trainingcourse.movie_home.entity.comment.commentDao

@Database(entities = [comment::class],version = 7)
abstract class commentDatabase : RoomDatabase() {
    abstract fun commentDao() : commentDao

    companion object
    {
        @Volatile
        private var INSTANCE : commentDatabase? = null

        fun getDatabase(context: Context) : commentDatabase
        {
            val instance = INSTANCE
            if (instance != null)
            {
                return instance
            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    commentDatabase::class.java,
                    "Database_comment"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return instance

            }
        }
    }
}