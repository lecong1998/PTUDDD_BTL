package gst.trainingcourse.movie_home.SignIn

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import gst.trainingcourse.movie_home.Activity.Appmovie
import gst.trainingcourse.movie_home.MainActivity
import gst.trainingcourse.movie_home.R
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment(),SignInContract.View {

    private lateinit var presenter: SignInContract.Present

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        login_fragment_btn_login?.setOnClickListener {
            presenter = SignInPresenter(this, UserRepository(requireContext()))
            val username = login_fragment_edt_username.text.toString()
            val password = login_fragment_edt_password.text.toString()

            presenter.CheckLogin(username, password)

        }
        login_fragment_btn_register?.setOnClickListener {
            it.findNavController().navigate(R.id.action_loginFragment_to_registerFragment)
        }
    }

    override fun showMessage(message: String) {

        Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
        if (message=="Login success"){
            activity?.let{
               var user =  presenter.getUser(login_fragment_edt_username.text.toString())
                val intent = Intent(context,Appmovie::class.java)
                intent.putExtra("username",login_fragment_edt_username.text.toString())
                startActivity(intent)
            }
        }

    }

}