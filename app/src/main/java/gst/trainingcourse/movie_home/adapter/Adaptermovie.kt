package gst.trainingcourse.movie_home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.movie
import kotlinx.android.synthetic.main.item_movie_home.view.*

class adaptermovie (var listmovie : List<movie> ) : RecyclerView.Adapter<adaptermovie.viewholder>() {
    inner class viewholder( var itemview: View) : RecyclerView.ViewHolder(itemview){
        fun onBindata(movie: movie)
        {
            itemview.tenphim_itemmovie.text = movie.tenphim
            Picasso.get().load(movie.image).into(itemview.image_itemmovie)
            //Glide.with(itemView.context).load(movie.image).into(itemview?.image_itemmovie)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewholder {
        return viewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_home,parent,false)
        )
    }

    override fun onBindViewHolder(holder: viewholder, position: Int) {
        return holder.onBindata(listmovie.get(position))
    }

    override fun getItemCount(): Int {
        return listmovie.size
    }
}