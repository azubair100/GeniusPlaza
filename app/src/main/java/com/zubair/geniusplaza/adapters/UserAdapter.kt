package com.zubair.geniusplaza.adapters

import android.view.ViewGroup
import androidx.paging.PagedListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zubair.geniusplaza.R
import com.zubair.geniusplaza.models.User


class UserAdapter : PagedListAdapter<User, RecyclerView.ViewHolder>(UserDiffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder
            = UserViewHolder.create(parent)

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as UserViewHolder).bindTo(getItem(position))
    }

    override fun getItemViewType(position: Int): Int = R.layout.item_user


    override fun getItemId(position: Int): Long = position.toLong()

    companion object {
        val UserDiffCallback = object : DiffUtil.ItemCallback<User>() {
            override fun areItemsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: User, newItem: User): Boolean {
                return oldItem == newItem
            }
        }
    }
}