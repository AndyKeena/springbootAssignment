package com.example.springbootassignment.controller

import com.example.springbootassignment.datasource.EmployeeRepository
import com.example.springbootassignment.model.Employee
import com.example.springbootassignment.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/employees")

class EmployeeController (val employeeService: EmployeeService) {

    @GetMapping("")
    fun getEmployees():Collection<Employee> = employeeService.getEmployees()

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    fun addEmployee(@RequestBody employee:Employee):Employee = employeeService.addEmployee(employee)

    @DeleteMapping("/delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteEmployee(): String =  employeeService.deleteAllEmployee()

    @PatchMapping("/update/{employeeId}")
     fun updateEmployee(@RequestBody employee: Employee, @PathVariable employeeId: Int):Employee = employeeService.updateEmployee(employee,employeeId)
    @GetMapping("/{firstName}")
    fun viewEmployeeByName(@PathVariable firstName: String):Employee?  = employeeService.viewEmployeeByName(firstName)

    @GetMapping("/nic/{startWith}")
    fun findNicStartsWith(@PathVariable startWith: String): Collection<Employee> {
        return employeeService.findNicStartsWith(startWith)
    }


    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteByEmployeeId(@PathVariable id :Int):Employee? = employeeService.deleteEmployee(id)

}