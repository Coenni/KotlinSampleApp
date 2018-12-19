package com.coenni.kotlin.demo.controller

import com.coenni.kotlin.demo.domain.Customer
import com.coenni.kotlin.demo.repository.CustomerRepository
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/customer")
class CustomerController (val repository: CustomerRepository) {

	@GetMapping("/")
	fun findAll() = repository.findAll()

	@GetMapping("/{lastName}")
	fun findByLastName(@PathVariable lastName:String)
		= repository.findByLastName(lastName)

	@PostMapping("/")
	fun saveCustomer(@RequestBody customer:Customer)
		= repository.save(customer)
}