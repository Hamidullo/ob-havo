package com.criminal_code.test_mvp.model.User

import android.text.TextUtils
import android.util.Patterns
import com.criminal_code.test_mvp.model.User.IUser
import javax.inject.Inject
import javax.inject.Named

class User @Inject constructor(@Named("email") override val email: String, @Named("password")  override val password: String) : IUser {
    override fun isDataValid(): Int {
        return when {
            TextUtils.isEmpty(email) -> 0
            !Patterns.EMAIL_ADDRESS.matcher(email).matches() -> 1
            password.length <= 6 -> 2
            else -> -1
        }
    }
}