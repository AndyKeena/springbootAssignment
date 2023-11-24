package com.example.springbootassignment.service

import com.example.springbootassignment.datasource.EmployeeRepository
import com.example.springbootassignment.model.Employee
import org.springframework.stereotype.Service
import java.util.NoSuchElementException

@Service
class EmployeeService(val employeeRepository: EmployeeRepository) {

    fun getEmployees(): Collection<Employee> {
        val employees = employeeRepository.findAll()
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

//    fun viewEmployeeByName(firstName: String):Unit {
//        employeeRepository.viewEmployeeByName(firstName)?.let {
//            return@let employeeRepository.viewEmployeeByName(firstName)
//        }
//    }

    fun viewEmployeeByName(firstName: String): Employee? {
        employeeRepository.findByFirstName(firstName)?.let {
            return employeeRepository.findByFirstName(firstName)
        } ?: throw IllegalArgumentException(" ${firstName} does not exist.")
    }



    fun findNICStartsWith90(): Collection<Employee> {
        val employees = employeeRepository.findAll()
            return employees.filter { employee ->
                val NICOfEmployee =  employee.NIC
                NICOfEmployee.startsWith("90")
            }
    }

    fun updateEmployee(employee: Employee ,id: Int): Employee = employeeRepository.findEmployeeById(id)?.let{addEmployee(employee)}
        ?: throw EmployeeNotFoundException("Employee not found")

    }
    class EmployeeNotFoundException(message: String) : RuntimeException(message)
