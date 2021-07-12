package com.criminal_code.test_mvp.model.User

interface IUser {
    val email: String
    val password: String
    fun isDataValid(): Int
}