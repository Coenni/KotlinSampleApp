package com.coenni.kotlin.demo.security

import com.coenni.kotlin.demo.domain.User
import org.springframework.security.core.Authentication

class UserAuthToken(var user: User) : Authentication {

    override fun getAuthorities() = null

    override fun setAuthenticated(isAuthenticated: Boolean) {}

    override fun getName() = user.login

    override fun getCredentials() = user.password

    override fun getPrincipal() = user

    override fun isAuthenticated() = true

    override fun getDetails() = user.toString()
}