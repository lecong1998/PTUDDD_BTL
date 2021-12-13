package gst.trainingcourse.movie_home.Activity

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.squareup.picasso.Picasso
import gst.trainingcourse.movie_home.DetailFilm.DetailActivity
import gst.trainingcourse.movie_home.DetailFilm.History_filmDatabase
import gst.trainingcourse.movie_home.Profile.*
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.SignIn.SignInActivity
import gst.trainingcourse.movie_home.SignIn.SignInDatabase
import gst.trainingcourse.movie_home.SignIn.UserRepository
import gst.trainingcourse.movie_home.api.APIClient
import gst.trainingcourse.movie_home.entity.History.history_film
import gst.trainingcourse.movie_home.entity.User.user
import gst.trainingcourse.movie_home.entity.detail.moviedetail
import gst.trainingcourse.movie_home.repository.MovieRepository
import kotlinx.android.synthetic.main.activity_appmovie.*
import kotlinx.android.synthetic.main.dialog_thaydoipassword.*
import kotlinx.android.synthetic.main.dialog_thaydoipassword.view.*
import kotlinx.android.synthetic.main.dialog_thongtincanhan.view.*
import kotlinx.android.synthetic.main.dulieudaxem_dialog.view.*
import kotlinx.android.synthetic.main.fragment_profile.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentProfile : Fragment(),OnClickItem_MovieWatched ,Profile_Contract.View{


    private lateinit var viewdialog : View
    private var user : user? = null

    private var username :String? = null

    lateinit var listmovie : ArrayList<moviedetail>
    lateinit var presenter: Profile_Contract.Presenter
    lateinit var listfilm: List<history_film>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments.let {
            username = it?.getString("username")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    @SuppressLint("ResourceType")
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        user = SignInDatabase.getDatabase(requireContext()).userDao().getUserusername(username!!)
        profile_text.text = username.toString()
        if(user?.img?.isNotEmpty() == true)
        {
            Picasso.get().load(user?.img!!).error(R.drawable._60279747_1127526494354946_6683273208343303265_n).into(profile_images)
        }
        btndangxuat.setOnClickListener {
            val intent = Intent(context,SignInActivity::class.java)
            startActivity(intent)
        }
        profile_timkiem.setOnClickListener {
            activity?.home_toolbar?.title = "Search"
            activity?.supportFragmentManager?.beginTransaction()
                ?.add(R.id.framelayout_movie,FragmentSearch(),FragmentSearch::class.java.name)
                ?.addToBackStack(FragmentSearch::class.java.name)
                ?.commit()
        }
        thongtincanhan.setOnClickListener {
            thongtincanhandialog()
        }

        profile_replace_password.setOnClickListener {
            thaydoimatkhau()
        }

        profile_lichsuxem.setOnClickListener{
            laydulieudaxem()
        }

    }

    @SuppressLint("ResourceAsColor")
    private fun laydulieudaxem() {
        presenter = Profile_Presenter(this,MovieRepository(requireContext()))
        presenter.getUIFilm(username!!)

        val DialogBuild : AlertDialog.Builder = AlertDialog.Builder(requireContext(),R.style.DialogFullScreen)
        var view : View = LayoutInflater.from(context).inflate(R.layout.dulieudaxem_dialog,null)
        DialogBuild.setView(view)
        val dialog = DialogBuild.create()
        dialog.show()



         listmovie   = ArrayList<moviedetail>()
        listfilm = ArrayList<history_film>()
        listfilm = History_filmDatabase.getDataBase(requireContext()).history_filmDao().getFilmWatched(username!!)

        if (listfilm == null)
        {
            return
        }

        if(listfilm!= null)
        {
            listfilm.forEach {
                val call: Call<moviedetail> = APIClient.getClient.getDetailMovie(activity?.getString(R.string.api_key)!!,it.id_movie)
                call.enqueue(object : Callback<moviedetail>{
                    @SuppressLint("NotifyDataSetChanged")
                    override fun onResponse(call: Call<moviedetail>, response: Response<moviedetail>)  {
                        if (response.isSuccessful)
                        {
                           // listmovie.add(response.body()!!)
                            var adapter = listmovie?.let { adapter_moviewatch(it,this@FragmentProfile) }
                            adapter.listmovie.add(response.body()!!)
                           // adapter?.notifyDataSetChanged()
                            //adapter.addmovidetail(response.body()!!)
                            view.history_watch_recyclerview.layoutManager = GridLayoutManager(requireContext(),2,GridLayoutManager.VERTICAL,false)
                            view.history_watch_recyclerview.adapter = adapter
                            Log.e("Test","Thành công")

                        }
                    }

                    override fun onFailure(call: Call<moviedetail>, t: Throwable) {
                        Log.e("Test","Thất bại")
                    }
                })
            }
        }

        view?.history_watch_recyclerview?.adapter?.notifyDataSetChanged()

        view.dulieuxem_back.setOnClickListener {
            dialog.dismiss()
        }

    }

    private fun thongtincanhandialog() {


        Log.e("loi",user?.username.toString())
        Toast.makeText(context,user?.fullname.toString(),Toast.LENGTH_LONG).show()

        val dialogBuilder : AlertDialog.Builder = AlertDialog.Builder(requireContext(),R.style.DialogProfile)
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_thongtincanhan,null)
        dialogBuilder.setView(view)
        val dialog = dialogBuilder.create()
        view.thongtincanhan_nameuser.text = user!!.username
        view.thongtincanhan_email.text = user!!.email
        view.thongtincanhan_fullname.text = user!!.fullname
        view.country.text = user!!.country
        if (user?.img?.isNotEmpty() == true)
        {
            Picasso.get().load(user?.img!!).error(R.drawable._60279747_1127526494354946_6683273208343303265_n).into(view.thongtincanhan_image)
        }

        dialog.show()
        view.back_thongtincanhan.setOnClickListener {
            dialog.dismiss()
        }

    }

    fun thaydoimatkhau()
    {
        val dialogBuilder : AlertDialog.Builder = AlertDialog.Builder(requireContext(),R.style.DialogProfile)
        val view = LayoutInflater.from(activity).inflate(R.layout.dialog_thaydoipassword,null)
        dialogBuilder.setView(view)
        val dialog = dialogBuilder.create()
        dialog.show()
        view.back_replace_password.setOnClickListener{
            dialog.dismiss()
        }
        view.btn_replace_password.setOnClickListener {
            if (view.replace_password_old.text.isEmpty()||
                    view.replace_repassword_old.text.isEmpty()||
                    view.replace_newpassword.text.isEmpty())
            {
                Toast.makeText(context,"Thông tin nhập chưa đầy đủ!",Toast.LENGTH_LONG).show()
            }
            else
            {
                if (view.replace_password_old.text.toString().equals(view.replace_repassword_old.text.toString()))
                {
                    if (view.replace_password_old.text.toString().equals(user?.password.toString()))
                    {

                        user?.password = view?.replace_newpassword?.text.toString()
                        SignInDatabase.getDatabase(requireContext()).userDao().updateUser(user!!)


                       // Toast.makeText(context,user?.password,Toast.LENGTH_SHORT).show()

                        Toast.makeText(context,"Thay đổi mật khẩu thành công!",Toast.LENGTH_LONG).show()
                        dialog.dismiss()
                    }
                    else
                    {
                        Toast.makeText(context,"Mật khẩu cũ không đúng", Toast.LENGTH_SHORT).show()
                    }

                }
                else
                {
                    Toast.makeText(context,"Mật khẩu cũ nhập không khớp!",Toast.LENGTH_SHORT).show()
                }
            }

        }
    }


    companion object {
        fun newInstance(username : String) = FragmentProfile().apply {
                arguments =Bundle().apply {
                    putString("username",username)
                }
        }
    }

    override fun ClickItem(moviedetail: moviedetail) {
        val intent = Intent(activity,DetailActivity::class.java)
        intent.putExtra("id_movie",moviedetail.id)
        intent.putExtra("username",username)
        startActivity(intent)
    }

    override fun getViewDataFilmWatched(listfilm: List<history_film>) {
        Log.e("listfilm",listfilm.toString())
    }


}
