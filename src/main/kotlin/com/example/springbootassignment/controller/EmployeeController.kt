package com.example.springbootassignment.controller

import com.example.springbootassignment.model.Employee
import com.example.springbootassignment.service.EmployeeService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import org.springframework.web.reactive.function.BodyInserters
import org.springframework.web.reactive.function.client.WebClient
import reactor.core.publisher.Mono
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.reactive.function.server.ServerResponse

@RestController
@RequestMapping("/api/employees")
class EmployeeController(
    val employeeService: EmployeeService
) {

    private val webClient: WebClient = WebClient.create()

    @GetMapping("")
    fun getEmployees(): Collection<Employee> = employeeService.getEmployees()

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    fun addEmployee(@RequestBody employee: Employee): Employee {
        val addedEmployee = employeeService.addEmployee(employee)
       sendNotificationToMobile(addedEmployee)
        return addedEmployee
    }

    private fun sendNotificationToMobile(employee: Employee) {
        val fcmUrl = "https://fcm.googleapis.com/fcm/send"
        val serverKey = "AAAAkIJT9DY:APA91bGUzKP6ihymjIBUmoDwk6mx49WHHXi1BvHFJCMEBP70Bavjx1lAs3wR2D8lakqLPD5iIHXwdrkJ2Rqd29A0qlp75pXjsyz8H9wRVQrv8_unoUXzOF1iheVEdewnPszGnzH2yfbw"
        val deviceToken = "f23aH_oXTTS5LytRMuKWrZ:APA91bGp7mbdGD8sfVjqfiaK_7m7WCsUlQlPGZ1jZfHVacCbHSEwINopp0I9mtL2Nwht8YYMsC8WCiDJHNgO687WbmVg31rT92_aL-rw3BGNystfNObJ56x8WS2XPZ-scqAdIeH1fi7M"

        val notification = mapOf(
            "title" to "New Employee Added",
            "body" to "New employee added: ${employee.firstName} ${employee.lastName}"
        )

        val body = mapOf(
            "notification" to notification,
            "to" to deviceToken
        )

        val resultMono: Mono<String> = webClient.post()
            .uri(fcmUrl)
            .header("Authorization", "key=$serverKey")
            .body(BodyInserters.fromValue(body))
            .retrieve()
            .bodyToMono(String::class.java)

        resultMono.subscribe { result ->
            println("Notification result: $result")
        }
    }

    @DeleteMapping("/delete")
    fun deleteEmployee(): String = employeeService.deleteAllEmployee()

    @PatchMapping("/update/{employeeId}")
    fun updateEmployee(@RequestBody employee: Employee, @PathVariable employeeId: Int): Employee =
        employeeService.updateEmployee(employee, employeeId)

    @GetMapping("/{firstName}")
    fun viewEmployeeByName(@PathVariable firstName: String): Employee? = employeeService.viewEmployeeByName(firstName)

    @GetMapping("/nic/{startWith}")
    fun findNicStartsWith(@PathVariable startWith: String): Collection<Employee> {
        return employeeService.findNicStartsWith(startWith)
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteByEmployeeId(@PathVariable id: Int): Employee? = employeeService.deleteEmployee(id)
}
