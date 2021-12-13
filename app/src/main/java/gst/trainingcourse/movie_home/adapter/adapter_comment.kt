package gst.trainingcourse.movie_home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.entity.comment.comment
import kotlinx.android.synthetic.main.item_comment.view.*

class adapter_comment(var listcomment : List<comment>) : RecyclerView.Adapter<adapter_comment.Viewholder>() {
    inner class Viewholder(var itemview: View) : RecyclerView.ViewHolder(itemview)  {
        fun onBindata(comment: comment)
        {
            itemview.username_itemcomment.text = comment.username
            itemview.content_comment.text = comment.comment
            itemview.time_comment.text = comment.datetime
            Picasso.get().load(comment.image).error(R.drawable._60279747_1127526494354946_6683273208343303265_n).into(itemview.imageview_user)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Viewholder {
        return Viewholder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_comment,parent,false)
        )
    }

    override fun onBindViewHolder(holder: Viewholder, position: Int) {
        return holder.onBindata(listcomment[position])
    }

    override fun getItemCount(): Int {
        return listcomment.size
    }
}