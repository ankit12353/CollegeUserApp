package com.android.collegeapp.base.adapter

import android.view.View
import android.view.ViewGroup
import androidx.viewbinding.ViewBinding
import androidx.viewpager.widget.PagerAdapter

abstract class BasePagerAdapter<T: Any,VB: ViewBinding> : PagerAdapter() {
//todo
    var list = mutableListOf<T>()

    fun addItems(list: MutableList<T>){
        list.clear()
        this.list = list
        notifyDataSetChanged()
    }


    override fun getCount() = list.size

    override fun isViewFromObject(view: View, `object`: Any) = (view == `object`)

    override fun destroyItem(container: ViewGroup, position: Int, `object`: Any) {
        container.removeView(`object` as View)
    }

    companion object {

    }
}