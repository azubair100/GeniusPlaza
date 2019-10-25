package com.zubair.geniusplaza.datasources

import androidx.lifecycle.MutableLiveData
import androidx.paging.DataSource
import com.zubair.geniusplaza.models.User
import com.zubair.geniusplaza.network.UserService
import io.reactivex.disposables.CompositeDisposable

class UsersDataSourceFactory(private val compositeDisposable: CompositeDisposable,
                             private val userService: UserService
)
    : DataSource.Factory<Int, User>() {

    private val usersDataSourceLiveData = MutableLiveData<UsersDataSource>()

    override fun create(): DataSource<Int, User>? {
        val usersDataSource = UsersDataSource(userService, compositeDisposable)
        usersDataSourceLiveData.postValue(usersDataSource)
        return usersDataSource
    }

}
