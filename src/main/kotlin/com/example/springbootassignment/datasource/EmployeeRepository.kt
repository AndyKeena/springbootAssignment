package com.example.springbootassignment.datasource

import com.example.springbootassignment.model.Employee
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.data.mongodb.repository.Query

interface EmployeeRepository:MongoRepository<Employee, String> {

    fun findEmployeeById(id: Int): Employee? //return type can be null
    fun findByFirstName(firstName: String): Employee?
}