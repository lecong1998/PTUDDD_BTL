package gst.trainingcourse.movie_home.Search

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.entity.Search.Result
import gst.trainingcourse.movie_home.entity.Search.Search
import kotlinx.android.synthetic.main.item_movie_home.view.*

class adapter_search (var Click : OnCLickItem_Search): RecyclerView.Adapter<adapter_search.Viewholder>(){

    private var datalist_search : List<Result> = ArrayList<Result>()

    fun setData(datalist : List<Result>)
    {
        datalist_search = datalist
    }

    class Viewholder(var itemview: View) : RecyclerView.ViewHolder(itemview) {
        fun onBindata(movie: Result)
        {
            itemview.tenphim_itemmovie.text = movie.title
            Picasso.get().load(movie.image).into(itemview.image_itemmovie)
            //Glide.with(itemView.context).load(movie.image).into(itemview?.image_itemmovie)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie_home,parent,false)
        )
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        var datamodel = datalist_search.get(position)
        holder.onBindata(datamodel)
        holder.itemview.setOnClickListener {
            Click.ClickItem_Search(datamodel)
        }
    }

    override fun getItemCount(): Int {
        return datalist_search.size
    }


}
interface OnCLickItem_Search{
    fun ClickItem_Search(result: Result)
}