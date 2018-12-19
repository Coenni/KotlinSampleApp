package com.coenni.kotlin.demo.repository

import com.coenni.kotlin.demo.domain.Customer
import org.springframework.data.repository.CrudRepository

interface CustomerRepository : CrudRepository<Customer, Long> {
	fun findByLastName(name: String): List<Customer>
}