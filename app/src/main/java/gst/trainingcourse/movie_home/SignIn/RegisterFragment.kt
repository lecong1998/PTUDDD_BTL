package gst.trainingcourse.movie_home.SignIn

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import gst.trainingcourse.movie_home.R
import kotlinx.android.synthetic.main.fragment_register.*

class RegisterFragment : Fragment() , SignInContract.View{

    private lateinit var presenter: SignInContract.Present

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = SignInPresenter(this, UserRepository(this.requireContext()))
        register_fragment_btn_register.setOnClickListener {

            val username = register_fragment_edt_username.text.toString()
            val password = register_fragment_edt_password.text.toString()
            val confPassword = register_fragment_edt_confirmpass.text.toString()
            val fullname = register_fragment_edt_name.text.toString()
            val email = register_fragment_edt_email.text.toString()
            val checkStatus = presenter.newUser(username,password,confPassword,fullname,email)
            if (checkStatus)
            {
                it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
            }

        }
        register_fragment_btn_cancel.setOnClickListener {
            it.findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }
    }

    override fun showMessage(message: String) {
        Toast.makeText(this.requireContext(), message, Toast.LENGTH_SHORT).show()
    }
}