package com.coenni.kotlin.demo.security

import com.coenni.kotlin.demo.domain.User
import com.coenni.kotlin.demo.repository.UserRepository
import com.fasterxml.jackson.databind.ObjectMapper
import org.springframework.security.core.Authentication
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter
import javax.servlet.FilterChain
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

class JWTSignInFilter (defaultFilterProcessesUrl: String?)
                : AbstractAuthenticationProcessingFilter(defaultFilterProcessesUrl) {

    private val userRepository = UserRepository()

    override fun attemptAuthentication(request: HttpServletRequest?,
                                       response: HttpServletResponse?): Authentication? {
        //getting user from request body
        val user = ObjectMapper().readValue(request!!.inputStream, User::class.java)
        if(userRepository.findUserByLoginAndPassword(user) != null) //if a user was found
            return UserAuthToken(user)
        return null
    }

    override fun successfulAuthentication(request: HttpServletRequest,
                                          response: HttpServletResponse,
                                          filterChain: FilterChain?,
                                          auth: Authentication) {
        TokenAuthenticationService.addAuthentication(auth.name, response)
    }

}