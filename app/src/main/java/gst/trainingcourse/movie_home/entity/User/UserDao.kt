package gst.trainingcourse.movie_home.entity.User

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy.REPLACE
import androidx.room.Query
import androidx.room.Update

@Dao
interface UserDao {

    @Insert(onConflict = REPLACE)
    fun newUser(user: user)

    @Update(onConflict = REPLACE)
    fun updateUser(user: user)

    @Query("SELECT * FROM user_table WHERE id = :id_user")
    fun getUser(id_user : Int) : user

    @Query("SELECT * FROM user_table WHERE username = :username")
    fun getUserusername(username: String) : user

    @Query("SELECT * FROM user_table WHERE username = :username AND password = :password")
    fun CheckLogin(username : String, password : String) : user

    @Query("SELECT username FROM user_table WHERE username = :username")
    fun CheckRegister(username: String) : String
}