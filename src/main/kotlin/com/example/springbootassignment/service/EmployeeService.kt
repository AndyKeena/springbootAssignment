package com.example.springbootassignment.service

import com.example.springbootassignment.datasource.EmployeeRepository
import com.example.springbootassignment.model.Employee
import org.springframework.stereotype.Service
import org.springframework.web.bind.annotation.RequestParam
import java.time.Year
import java.util.NoSuchElementException

@Service
class EmployeeService(val employeeRepository: EmployeeRepository) {

    fun getEmployees(): Collection<Employee> {
        val employees = employeeRepository.findAll()
        println("----------------------------------------------------------------------------------")
        println(employees)
        println("----------------------------------------------------------------------------------")

        if (employees.isEmpty()){
            throw NoSuchElementException("No employees in the system")
        }
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

    fun deleteEmployee(id: Int): Employee? {
       employeeRepository.findEmployeeById(id)?.let {
           return employeeRepository.deleteById(id)
       } ?: throw IllegalArgumentException("Employee does not exist.")
   }

    fun viewEmployeeByName(firstName: String): Employee? {
        employeeRepository.findByFirstName(firstName)?.let {
            return employeeRepository.findByFirstName(firstName)
        } ?: throw IllegalArgumentException(" ${firstName} does not exist.")
    }



    fun findNicStartsWith(@RequestParam startingDigits: String): Collection<Employee> {
        val employees = employeeRepository.findAll()
            return employees.filter { employee ->
//                val nicOfEmployee =  employee.NIC
                employee.NIC?.startsWith(startingDigits) ?: throw Exception("NIC not found")
            }
    }

    fun findEmployeesWorkedMoreThan5Years(): Collection<Employee> {
        val currentYear = Year.now().value
        val employees = employeeRepository.findAll()

        return employees.filter { employee ->
            val yearsWorked = currentYear - employee.yearJoined
            yearsWorked > 5
        }
    }






    fun updateEmployee(employee: Employee ,id: Int): Employee = employeeRepository.findEmployeeById(id)?.let{addEmployee(employee)}
        ?: throw EmployeeNotFoundException("Employee not found")

    }
    class EmployeeNotFoundException(message: String) : RuntimeException(message)
