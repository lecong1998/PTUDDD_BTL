package gst.trainingcourse.movie_home.DetailFilm

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.squareup.picasso.Picasso
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.entity.detail.Actor
import kotlinx.android.synthetic.main.item_cast.view.*

class adapter_actor(var Listactor : List<Actor>): RecyclerView.Adapter<adapter_actor.ViewHolder>() {
    inner class ViewHolder(var itemview: View) : RecyclerView.ViewHolder(itemview) {

        fun onBindata(actor: Actor)
        {
           // Picasso.get().load(actor.image.toString()).error(R.drawable._60279747_1127526494354946_6683273208343303265_n).into(itemview.image_cast)

            itemview.tendienvien.text= actor.name
            Glide.with(itemview.context).load(actor.image.toString()).into(itemview.image_cast)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cast,parent,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        return holder.onBindata(Listactor.get(position))
    }

    override fun getItemCount(): Int {
        return Listactor.size
    }
}