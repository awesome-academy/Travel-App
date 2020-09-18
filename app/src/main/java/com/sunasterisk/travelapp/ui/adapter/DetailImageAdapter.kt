package com.sunasterisk.travelapp.ui.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager.widget.PagerAdapter
import com.sunasterisk.travelapp.R
import com.sunasterisk.travelapp.utils.setImageUrl
import kotlinx.android.synthetic.main.item_image_slider.view.*

class DetailImageAdapter : PagerAdapter() {

    private val imageUrls: MutableList<String> = mutableListOf()

    override fun instantiateItem(parent: ViewGroup, position: Int): View =
        LayoutInflater.from(parent.context)
            .inflate(R.layout.item_image_slider, parent, false)
            .also {
                parent.addView(it)
                it.imageViewSliderItem.setImageUrl(imageUrls[position])
            }

    override fun isViewFromObject(view: View, ob: Any) = view === ob

    override fun getCount() = imageUrls.size

    override fun destroyItem(container: ViewGroup, position: Int, ob: Any) =
        container.removeView(ob as View)

    fun updateList(imageUrls: List<String>) {
        this.imageUrls.clear()
        this.imageUrls.addAll(imageUrls)
        notifyDataSetChanged()
    }
}
