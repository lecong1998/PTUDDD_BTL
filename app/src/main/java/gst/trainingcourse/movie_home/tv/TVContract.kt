package gst.trainingcourse.movie_home.tv

import gst.trainingcourse.movie_home.entity.TopTV.Item

interface TVContract {
    interface View{
        fun updateDataTopTV(datalisttoptv : List<Item>)
        fun updateDataPopularTV(datalistpopulartv : List<gst.trainingcourse.movie_home.entity.MostPopularTV.Item>)
    }
    interface Presenter{
        fun getDataTopTV()
        fun updateUITopTV(datalisttoptv: List<Item>)

        fun getDataPopularTV()
        fun updateUIPopularTV(datalistpopulartv: List<gst.trainingcourse.movie_home.entity.MostPopularTV.Item>)
    }
}