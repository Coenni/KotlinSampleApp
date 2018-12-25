package com.coenni.kotlin.demo.controller

import com.coenni.kotlin.demo.domain.User
import com.coenni.kotlin.demo.repository.UserRepository
import com.nimbusds.jose.JWSAlgorithm
import com.nimbusds.jose.JWSHeader
import com.nimbusds.jose.crypto.MACSigner
import com.nimbusds.jose.crypto.MACVerifier
import com.nimbusds.jwt.JWTClaimsSet
import com.nimbusds.jwt.SignedJWT
import org.springframework.web.bind.annotation.*
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

@RestController
@RequestMapping("/users")
class UserResource {

    private var userRepository = UserRepository()

    @RequestMapping("/all",method = [(RequestMethod.GET)])
    fun getAllUsers() = userRepository.getAllUsers()

    @RequestMapping(method = [(RequestMethod.POST)])
    @CrossOrigin("*")
    fun signIn(servletResponse: HttpServletResponse,
               @RequestBody user: User): User? {
        val foundUser = userRepository.findUserByLoginAndPassword(user)
        return foundUser
    }

    @RequestMapping("/signup", method = [(RequestMethod.POST)])
    @CrossOrigin("*")
    fun singUp(@RequestBody user: User): User? {
        val foundUser = userRepository.findUserByLoginAndPassword(user)
        if (foundUser != null)
            return null
        userRepository.addUser(user)
        return user
    }

    @RequestMapping("/secret", method = [(RequestMethod.GET)])
    @CrossOrigin("*")
    fun importantRouteWithSecretData(servletRequest: HttpServletRequest): String {
        return "You're accessing a protected endpoint :)"
    }
}