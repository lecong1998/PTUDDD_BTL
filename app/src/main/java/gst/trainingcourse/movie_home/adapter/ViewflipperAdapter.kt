package gst.trainingcourse.movie_home.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import gst.trainingcourse.movie_home.OnClickItem
import gst.trainingcourse.movie_home.R
import gst.trainingcourse.movie_home.entity.intheater.Item
import kotlinx.android.synthetic.main.item_movie_home.view.*
import kotlinx.android.synthetic.main.itemviewflipper.view.*

class ViewflipperAdapter(var CLickItem : OnClickItem) : BaseAdapter() {

    private var dataList: List<Item> = ArrayList<Item>()
    fun setData(list: List<Item>){
        dataList = list
    }

    override fun getCount(): Int {
        return dataList.size
    }

    override fun getItem(p0: Int): Any {
        return dataList[p0]
    }

    override fun getItemId(p0: Int): Long {
        return p0.toLong()
    }

    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        val view : View = LayoutInflater.from(p2?.context).inflate(R.layout.itemviewflipper,null)
        view.namefilm_viewflipper.text = dataList[p0].fullTitle
        Picasso.get().load(dataList[p0].image).error(R.drawable.anh1).into(view?.image_viewflipper)
        view.setOnClickListener {
            CLickItem.OnClickItem_IntheaterMovie(dataList[0])
        }
        return view
    }
}
