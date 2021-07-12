package com.criminal_code.test_mvp.di

import com.criminal_code.test_mvp.model.User.IUser
import com.criminal_code.test_mvp.model.User.User
import dagger.Binds
import dagger.Module

@Module
abstract class UserModule {
    @Binds
    abstract fun bindUser(user: User): IUser
}