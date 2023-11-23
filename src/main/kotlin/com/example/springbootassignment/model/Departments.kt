package com.example.springbootassignment.model

import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "departments")
data class Departments(
    @Id
    val departmentId: Int,
    val departmentName: String
)
