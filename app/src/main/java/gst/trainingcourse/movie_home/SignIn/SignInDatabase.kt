package gst.trainingcourse.movie_home.SignIn

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import gst.trainingcourse.movie_home.entity.History.history_film
import gst.trainingcourse.movie_home.entity.History.history_filmDao
//import gst.trainingcourse.movie_home.entity.History.history_filmDao
import gst.trainingcourse.movie_home.entity.User.UserDao
import gst.trainingcourse.movie_home.entity.User.user

@Database(entities = [user::class],version = 1)
abstract class SignInDatabase : RoomDatabase() {

    abstract fun userDao() : UserDao
    companion object
    {
        @Volatile
        private var INSTANCE :SignInDatabase? = null
        fun getDatabase (context: Context) : SignInDatabase {
            val instance = INSTANCE
            if (instance != null)
            {
                return  instance

            }
            synchronized(this)
            {
                val instance = Room.databaseBuilder(
                    context.applicationContext ,
                    SignInDatabase::class.java,
                    "Database"
                ).allowMainThreadQueries()
                    .build()
                INSTANCE = instance
                return  instance
            }
        }
    }
}