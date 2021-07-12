package com.criminal_code.test_mvp.presenter.Login

import com.arellomobile.mvp.InjectViewState
import com.arellomobile.mvp.MvpPresenter
import com.criminal_code.test_mvp.di.DaggerUserComponent
import com.criminal_code.test_mvp.di.UserComponent
import com.criminal_code.test_mvp.model.User.IUser
import com.criminal_code.test_mvp.view.ILoginView
import javax.inject.Inject

@InjectViewState
class LoginPresenter(): MvpPresenter<ILoginView>() {

    @Inject
    lateinit var user: IUser

    fun onLogin(email: String, password: String) {

        val component: UserComponent = DaggerUserComponent.builder().email(email).password(password).build()
        user = component.getUser()

        when (user.isDataValid()) {
            0 -> viewState.onLoginError("Email must not be null!")
            1 -> viewState.onLoginError("Wrong Email address")
            2 -> viewState.onLoginError("Password must be greater than 6")
            else -> viewState.onLoginSuccess("Login Success")
        }
    }
}