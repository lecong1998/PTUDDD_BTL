package gst.trainingcourse.movie_home.SignIn

import android.content.Context
import gst.trainingcourse.movie_home.entity.User.user

class UserRepository(var context: Context) {

    private val userDao = SignInDatabase.getDatabase(context).userDao()

    fun newUser(user: user)
    {
        userDao.newUser(user)
    }
    fun updateUser(user: user)
    {
        userDao.updateUser(user)
    }
    fun getUser(id_user : Int) : user
    {
        return userDao.getUser(id_user)
    }
    fun getUsersuccess(username: String): user
    {
        return userDao.getUserusername(username)
    }
    fun CheckLogin(username : String , password : String) : user
    {

        val user = userDao.CheckLogin(username, password)
        return user

    }
    fun CheckRegister(username: String) : String
    {
        return  userDao.CheckRegister(username)
    }

}