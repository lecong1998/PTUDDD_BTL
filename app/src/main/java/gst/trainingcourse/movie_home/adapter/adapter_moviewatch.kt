package gst.trainingcourse.movie_home.Profile

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.entity.detail.moviedetail
import kotlinx.android.synthetic.main.item_movie_home.view.*

class adapter_moviewatch(var listmovie : ArrayList<moviedetail>, var ClickItem : OnClickItem_MovieWatched) : RecyclerView.Adapter<adapter_moviewatch.ViewHolder>()  {
    inner class ViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun OnBinData(moviedetail: moviedetail)
        {
            //Picasso.get().load(moviedetail.image).error(R.drawable.anh3).into(itemview.image_itemmovie)
            itemview.tenphim_itemmovie.text = moviedetail.title
            Glide.with(itemView.context).load(moviedetail.image).into(itemview?.image_itemmovie)
        }

    }

    fun addmovidetail(moviedetail: moviedetail)
    {
        listmovie.add(moviedetail)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_home,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val moviewatchitem = listmovie[position]
         holder.OnBinData(moviewatchitem)
        holder.itemview.setOnClickListener {
            ClickItem.ClickItem(moviewatchitem)
        }
    }

    override fun getItemCount(): Int {
        return listmovie.size
    }
}
interface OnClickItem_MovieWatched{
    fun ClickItem(moviedetail: moviedetail)
}