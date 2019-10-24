package com.zubair.geniusplaza.network

import com.google.gson.annotations.SerializedName
import com.zubair.geniusplaza.models.User

data class UserListResponse(@SerializedName("page") val pageId: Int,
                            @SerializedName("total_pages") val totalPage: Int,
                            @SerializedName("data") val users: List<User>)