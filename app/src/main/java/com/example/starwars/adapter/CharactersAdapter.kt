package com.example.starwars.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.starwars.R
import com.example.starwars.model.Result
import kotlinx.android.synthetic.main.characters_item_layout.view.*

class CharactersAdapter : RecyclerView.Adapter<CharactersAdapter.ItemViewHolder>() {

    private val differCallBack = object : DiffUtil.ItemCallback<Result>(){
        override fun areItemsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: Result, newItem: Result): Boolean {
            return oldItem == newItem
        }

    }

    val differ = AsyncListDiffer(this,differCallBack)

    inner class ItemViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        return ItemViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.characters_item_layout,parent,false))
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val result = differ.currentList[position]
        holder.itemView.apply {
            this.tvTitle.text = result.name
            setOnClickListener{
                onItemClickLister?.let {
                    it(result)
                }
            }
        }
    }

    override fun getItemCount(): Int {
        return  differ.currentList.size
    }

    private var onItemClickLister : ((Result)->Unit)? = null

    fun setOnItemClickListener(listener :(Result)->Unit){
        onItemClickLister = listener
    }
}