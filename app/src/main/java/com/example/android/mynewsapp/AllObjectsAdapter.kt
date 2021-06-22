package com.example.android.mynewsapp

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView

import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.mynewsapp.databinding.ViewHolderBinding
import com.example.android.mynewsapp.util.DomainObject

class AllObjectsAdapter(private val onObjectClickListener: OnObjectClickListener):
    ListAdapter<DomainObject, AllObjectsAdapter.AllObjectsViewHolder>(DiffUtilCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AllObjectsViewHolder {
       return AllObjectsViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: AllObjectsViewHolder, position: Int) {
        val domainObject =getItem(position)
        holder.bind(domainObject,onObjectClickListener)
    }

    class AllObjectsViewHolder(val binding: ViewHolderBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(domainObject: DomainObject, onItemClickListener: OnObjectClickListener){
            binding.clickListener = onItemClickListener
            binding.`object` = domainObject
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): AllObjectsViewHolder{
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding =ViewHolderBinding.inflate(layoutInflater,parent,false)
                return AllObjectsViewHolder(binding)
            }

        }
    }

    class DiffUtilCallBack(): DiffUtil.ItemCallback<DomainObject>(){
        override fun areItemsTheSame(oldItem: DomainObject, newItem: DomainObject): Boolean {
           return oldItem == newItem
        }

        override fun areContentsTheSame(oldItem: DomainObject, newItem: DomainObject): Boolean {
           return oldItem.content == newItem.content
        }

    }

    class OnObjectClickListener(val onObjectClickListener: (domainObject: DomainObject) -> Unit){
        fun onClick(domainObject: DomainObject) = onObjectClickListener(domainObject)
    }

}



