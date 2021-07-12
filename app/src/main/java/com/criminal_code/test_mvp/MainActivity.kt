package com.criminal_code.test_mvp

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.arellomobile.mvp.MvpAppCompatActivity
import com.arellomobile.mvp.presenter.InjectPresenter
import com.criminal_code.test_mvp.presenter.Login.LoginPresenter
import com.criminal_code.test_mvp.view.ILoginView
import es.dmoral.toasty.Toasty

class MainActivity : MvpAppCompatActivity(), ILoginView {

    @InjectPresenter
    lateinit var loginPresenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val login: Button = findViewById(R.id.login)
        val email: TextView = findViewById(R.id.email)
        val password: TextView = findViewById(R.id.password)

        login.setOnClickListener {
            loginPresenter.onLogin(email.text.toString(),password.text.toString())
        }
    }

    override fun onLoginSuccess(message: String) {
        Toasty.success(this, message,Toast.LENGTH_SHORT).show()
        startActivity(Intent(this, MainActivity3::class.java))
    }

    override fun onLoginError(message: String) {
        Toasty.error(this, message,Toast.LENGTH_SHORT).show()
    }
}