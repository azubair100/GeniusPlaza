package com.zubair.geniusplaza.dependecy_injection

import com.zubair.geniusplaza.network.UserService
import com.zubair.geniusplaza.viewmodels.ListViewModel
import com.zubair.geniusplaza.viewmodels.NewUserViewModel
import dagger.Component

@Component(modules = [ApiModule::class])
interface ApiComponent {
    fun inject(service : UserService)
    fun inject(listViewModel: ListViewModel)
    fun inject(newUserViewModel: NewUserViewModel)
}