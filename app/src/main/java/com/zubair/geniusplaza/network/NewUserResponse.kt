package com.zubair.geniusplaza.network

import com.google.gson.annotations.SerializedName

class NewUserResponse(@SerializedName("id") val id: Int,
                      @SerializedName("first_name") val firstName: String,
                      @SerializedName("last_name") val lastName: String,
                      @SerializedName("email") val email: String)
