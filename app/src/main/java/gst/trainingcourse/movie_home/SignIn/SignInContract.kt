package gst.trainingcourse.movie_home.SignIn

import gst.trainingcourse.movie_home.entity.User.user

interface SignInContract {
    interface View {
        fun showMessage(message : String)
    }
    interface Present{
        fun CheckLogin(username : String, password : String)
        fun newUser(username :String,password:String,confPassword : String, fullname: String, email:String) : Boolean
        fun getUser(username : String) : user
    }
}