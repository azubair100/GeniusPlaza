package com.zubair.geniusplaza.network

import com.zubair.geniusplaza.dependecy_injection.DaggerApiComponent
import com.zubair.geniusplaza.models.NewUser
import io.reactivex.Single
import javax.inject.Inject

class UserService {
    @Inject
    lateinit var api : UserApi
    init { DaggerApiComponent.create().inject(this) }

    fun getUsers(page : Int) : Single<UserListResponse> = api.getUsers(page)
    fun createUser(user: NewUser)  : Single<NewUserResponse> = api.createUser(user)
}