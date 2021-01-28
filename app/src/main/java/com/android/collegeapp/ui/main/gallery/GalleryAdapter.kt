package com.android.collegeapp.ui.main.gallery

import com.android.collegeapp.R
import com.android.collegeapp.adapter.BaseRVAdapter
import com.android.collegeapp.databinding.FragmentGalleryBinding
import com.android.collegeapp.databinding.LayoutItemGalleryBinding
import com.squareup.picasso.Picasso

class GalleryAdapter : BaseRVAdapter<String,LayoutItemGalleryBinding>() {
    override fun layout() = R.layout.layout_item_gallery

    override fun onBindViewHolder(
        holder: Companion.BaseViewHolder<LayoutItemGalleryBinding>,
        position: Int
    ) {
        val url = list[position]
        Picasso.get().load(url).into(holder.binding.ivGalleryImage)
    }
}