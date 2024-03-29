package com.example.springbootassignment.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document
import java.time.LocalDate

@Document(collection = "employee")
data class Employee(
    @Id
    val id: Int,
    val firstName: String,
    val lastName: String,
    val NIC: String?,
    val yearJoined: Int,
    val departmentId: Int?
)