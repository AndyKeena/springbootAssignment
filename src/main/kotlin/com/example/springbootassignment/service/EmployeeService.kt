package com.example.springbootassignment.service

import com.example.springbootassignment.datasource.EmployeeRepository
import com.example.springbootassignment.model.Employee
import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class EmployeeService(val employeeRepository: EmployeeRepository) {

    fun getEmployees(): Collection<Employee> {
        val employees = employeeRepository.findAll()
        return employees
    }

    fun addEmployee(employee:Employee):Employee{
        return  employeeRepository.save(employee)
    }

    fun deleteAllEmployee():String {
         employeeRepository.deleteAll()
         return "Done";
    }


    fun findEmployeeById(id: Int) {
        employeeRepository.findEmployeeById(id)?.let {
            return@let employeeRepository.findEmployeeById(id)
        }
    }

    fun updateEmployee(employee: Employee ,id: Int): Employee {
         val existingEmployee=employeeRepository.findEmployeeById(id)
        return  addEmployee(employee)
    }

}