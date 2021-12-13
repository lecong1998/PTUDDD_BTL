package gst.trainingcourse.movie_home.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.entity.comingsoon.Item
import kotlinx.android.synthetic.main.item_movie_home.view.*

class HomeComingSoonAdapter(var ClickItem : OnCLickItem_ComingSoon) : RecyclerView.Adapter<HomeComingSoonAdapter.Viewholder>() {

    private var dataList: List<Item> = ArrayList<Item>()
    fun setData(list: List<Item>){
        dataList = list
    }
    inner class Viewholder(var itemview : View) : RecyclerView.ViewHolder(itemview) {
        fun onBindData(m: Item) {
            itemView.tenphim_itemmovie.text = m.title
            Picasso.get().load(m.image).error(R.drawable.anh1).into(itemview?.image_itemmovie)
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
            ClickItem.OnClickItem_ComingSoonMovie(m)
        }
    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

interface OnCLickItem_ComingSoon{
    fun OnClickItem_ComingSoonMovie(item: gst.trainingcourse.movie_home.entity.comingsoon.Item)
}