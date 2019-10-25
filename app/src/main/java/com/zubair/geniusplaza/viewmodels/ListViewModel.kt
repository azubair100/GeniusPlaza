package com.zubair.geniusplaza.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.paging.LivePagedListBuilder
import androidx.paging.PagedList
import com.zubair.geniusplaza.datasources.UsersDataSourceFactory
import com.zubair.geniusplaza.dependecy_injection.DaggerApiComponent
import com.zubair.geniusplaza.models.User
import com.zubair.geniusplaza.network.UserService
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class ListViewModel : ViewModel(){
    var users: LiveData<PagedList<User>>
    private val disposable = CompositeDisposable()
    private val sourceFactory: UsersDataSourceFactory
    @Inject
    lateinit var userService: UserService


    init {
        DaggerApiComponent.create().inject(this)
        sourceFactory = UsersDataSourceFactory(disposable, userService)
        val config = PagedList.Config.Builder()
            .setPageSize(6)
            .setInitialLoadSizeHint(6)
            .setEnablePlaceholders(false)
            .build()
        users = LivePagedListBuilder<Int, User>(sourceFactory, config).build()
    }

    override fun onCleared() {
        super.onCleared()
        disposable.dispose()
    }
}