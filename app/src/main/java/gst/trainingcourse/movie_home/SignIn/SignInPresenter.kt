package gst.trainingcourse.movie_home.SignIn

import gst.trainingcourse.movie_home.entity.User.user

class SignInPresenter(
    private val View : SignInContract.View,
    private val userRepository: UserRepository
) : SignInContract.Present{
    override fun CheckLogin(username: String, password: String) {
       val userCheck = userRepository.CheckLogin(username, password)
        when (LoginValidation.validation(userCheck,username,password)) {
            "empty" -> View.showMessage("Username or password is empty")
            "incorrect" -> View.showMessage("Incorrect username/password or user does not exist")
            "ok" -> {
                View.showMessage("Login success")
            }
            "failed" -> View.showMessage("Login failed")
        }

    }

    override fun newUser(
        username: String,
        password: String,
        confPassword: String,
        fullname: String,
        email: String
    ): Boolean {
        val username_check =userRepository.CheckRegister(username)
        when (RegisterValidation.validation(username,username_check,password,confPassword)) {
            "empty" -> {
                View.showMessage("Username or password is empty")
                return false
            }
            "duplicate" -> {
                View.showMessage("User already exist")
                return false
            }
            "mismatch" -> {
                View.showMessage("Password and confirm password does not match")
                return false
            }
            "ok" -> {
                val user = user(username, password, fullname,email)
                userRepository.newUser(user)
                View.showMessage(user.fullname.toString()+" "+user.email.toString()+" Registed successfully")
                return true
            }
            else -> {
                View.showMessage("Registration failed")
                return false
            }
        }
    }

    override fun getUser(username: String) : user {
        return userRepository.getUsersuccess(username)
    }


}