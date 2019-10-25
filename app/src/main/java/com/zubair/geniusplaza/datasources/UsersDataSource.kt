package com.zubair.geniusplaza.datasources

import androidx.paging.PageKeyedDataSource
import com.zubair.geniusplaza.models.User
import com.zubair.geniusplaza.network.UserService
import io.reactivex.Completable
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.functions.Action

class UsersDataSource(private val userService: UserService, private val compositeDisposable: CompositeDisposable) : PageKeyedDataSource<Int, User>() {

    private var retryCompletable: Completable? = null

    override fun loadInitial(
        params: LoadInitialParams<Int>,
        callback: LoadInitialCallback<Int, User>
    ) {

        compositeDisposable.add(userService.getUsers(1).subscribe({ usersResponse ->
            setRetry(null)
            callback.onResult(usersResponse.users, null, 2)
        }, {
            setRetry(Action { loadInitial(params, callback) })
        }))
    }


    override fun loadAfter(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {
        compositeDisposable.add(userService.getUsers(params.key).subscribe({ usersResponse ->
            setRetry(null)
            val nextKey = if (params.key == usersResponse?.totalPage) null else params.key + 1
            callback.onResult(usersResponse.users, nextKey)
        }, {
            setRetry(Action { loadAfter(params, callback) })
        }))
    }

    override fun loadBefore(params: LoadParams<Int>, callback: LoadCallback<Int, User>) {}


    private fun setRetry(action: Action?) {
        if (action == null) {
            this.retryCompletable = null
        } else {
            this.retryCompletable = Completable.fromAction(action)
        }
    }
}
