package gst.trainingcourse.movie_home.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import gst.trainingcourse.movie_home.OnClickItem
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.entity.TopMovie.Film
import kotlinx.android.synthetic.main.item_movie_home.view.*

class MovieAdapter(var OnClickItem : OnClickItem_TopMovie) : RecyclerView.Adapter<MovieAdapter.Viewholder>() {

    private var dataList: List<Film> = ArrayList<Film>()
    fun setData(list: List<Film>){
        dataList = list
    }
   inner class Viewholder(var itemview : View) : RecyclerView.ViewHolder(itemview) {
       fun onBindData(m: Film) {
           itemView.tenphim_itemmovie.text = m.title
           //Picasso.get().load(m.image).error(R.drawable.anh1).into(itemview?.image_itemmovie)
           Glide.with(itemView.context).load(m.image).into(itemview?.image_itemmovie)
       }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.item_movie_home, parent, false)
        )
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        val m = dataList.get(position)
        holder.onBindData(m)
        holder.itemview.setOnClickListener {
            OnClickItem.OnClickItem_Topmovie(m)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

interface OnClickItem_TopMovie{
    fun OnClickItem_Topmovie(film: Film)
}