package com.criminal_code.test_mvp.view

import com.arellomobile.mvp.MvpView

interface ILoginView: MvpView {
    fun onLoginSuccess(message: String)
    fun onLoginError(message: String)
}