package com.zubair.geniusplaza.viewmodels

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.zubair.geniusplaza.dependecy_injection.DaggerApiComponent
import com.zubair.geniusplaza.models.NewUser
import com.zubair.geniusplaza.network.NewUserResponse
import com.zubair.geniusplaza.network.UserService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class NewUserViewModel: ViewModel() {
    val newUserCreated = MutableLiveData<Boolean>()
    private val disposable = CompositeDisposable()
    @Inject
    lateinit var userService: UserService

    init { DaggerApiComponent.create().inject(this)}

    fun createNewUser(user : NewUser){
        disposable.add(
            userService.createUser(user)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(object : DisposableSingleObserver<NewUserResponse>() {
                    override fun onSuccess(response: NewUserResponse) {
                        if(response.email == user.email) newUserCreated.value = true
                    }
                    override fun onError(e: Throwable) { newUserCreated.value = false}
                }
                )
        )
    }

    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}