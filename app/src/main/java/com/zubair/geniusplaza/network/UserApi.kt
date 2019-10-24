package com.zubair.geniusplaza.network

import com.zubair.geniusplaza.models.NewUser
import io.reactivex.Single
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Query

interface UserApi {
    @GET("api/users") fun getUsers(@Query("page") page : Int) : Single<UserListResponse>
    @POST("api/users") fun createUser(@Body newUser : NewUser) : Single<NewUserResponse>
}