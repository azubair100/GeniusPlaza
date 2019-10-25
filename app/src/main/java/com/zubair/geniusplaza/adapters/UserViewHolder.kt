package com.zubair.geniusplaza.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.zubair.geniusplaza.R
import com.zubair.geniusplaza.models.User
import kotlinx.android.synthetic.main.item_user.view.*

class UserViewHolder(view: View) : RecyclerView.ViewHolder(view) {

    fun bindTo(user: User?) {
        itemView.UserName.text = user?.firstName + " " + user?.lastName
        Picasso.get().load(user?.url).placeholder(R.drawable.default_picture).into(itemView.UserAvatar)
    }

    companion object {
        fun create(parent: ViewGroup): UserViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val view = layoutInflater.inflate(R.layout.item_user, parent, false)
            return UserViewHolder(view)
        }
    }

}