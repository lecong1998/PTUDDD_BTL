package gst.trainingcourse.movie_home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import gst.trainingcourse.movie_home.Profile.item
import gst.trainingcourse.movie_home.R
import kotlinx.android.synthetic.main.item_profile.view.*

class adapter_profile(var listtitle : List<item>) : RecyclerView.Adapter<adapter_profile.Viewholder>() {
    inner class Viewholder(var itemview : View) : RecyclerView.ViewHolder(itemview) {

        fun onBindata(item: item)
        {
            itemview.profile_text.text = item.name_title
            itemview.profile_text2.text = item.name
            itemview.profile_images.setImageResource(item.image)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return  Viewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_profile,parent,false)
        )
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        return holder.onBindata(listtitle.get(position))
    }

    override fun getItemCount(): Int {
        return listtitle.size
    }
}