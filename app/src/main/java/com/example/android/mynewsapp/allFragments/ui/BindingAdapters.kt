package com.example.android.mynewsapp.allFragments.ui

import android.widget.ImageView
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mynewsapp.GlideApp
import com.example.android.mynewsapp.R
import com.example.android.mynewsapp.allFragments.dataLayer.DomainObject

@BindingAdapter("imageUrl")
fun bindImage(imgView: ImageView, imageUrl: String?){

imageUrl?.let {
val imageUr =imageUrl.toUri().buildUpon().scheme("https").build()
    GlideApp.with(imgView.context)
        .load(imageUr)
        .placeholder(R.drawable.loading_animation)
        .fitCenter()
        .error(R.drawable.ic_broken_image)
        .into(imgView)
}
}

@BindingAdapter("listData")
fun bindRecyclerViewData(recyclerView: RecyclerView, data: List<DomainObject>?){
    val adapter =recyclerView.adapter as AllObjectsAdapter
    adapter.submitList(data)
}