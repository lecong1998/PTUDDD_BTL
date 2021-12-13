package gst.trainingcourse.movie_home.SignIn

import gst.trainingcourse.movie_home.entity.User.user

object LoginValidation {
    fun validation(userCheck : user?, username:String, password : String) : String{
        return if (username.isEmpty() || password.isEmpty())
            "empty"
        else if (userCheck == null)
            "incorrect"
        else if (userCheck.username == username && userCheck.password == password){
            "ok"
        }
        else
            "failed"
    }
}