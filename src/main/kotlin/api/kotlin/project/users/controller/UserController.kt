package api.kotlin.project.users.controller

import api.kotlin.project.users.dto.NewUserForm
import api.kotlin.project.users.dto.UpdateUserForm
import api.kotlin.project.users.dto.UserView
import api.kotlin.project.users.service.UserService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriComponentsBuilder

//http://localhost:8080/swagger-ui/index.html
@RestController
@RequestMapping("/users")
class UserController(private val service: UserService) {

    @GetMapping
    fun findAll(
        @PageableDefault(size = 10) pagination: Pageable
    ): Page<UserView> {
        return service.findAll(pagination)
    }

    @GetMapping("/{id}")
    fun searchForId(@PathVariable id: Long): UserView {
        return service.findById(id)
    }

    @GetMapping("/byDay")
    fun perDay(@RequestParam(required = false) date: String?): Long {
        return service.findByDateAdd(date)
    }

    @PostMapping
    @Transactional
    fun create(
        @RequestBody @Valid
        user: NewUserForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UserView> {
        val userView = service.create(user)
        val uri = uriBuilder.path("/users/${userView.id}").build().toUri()
        return ResponseEntity.created(uri).body(userView)
    }

    @PutMapping
    @Transactional
    fun update(@RequestBody @Valid form: UpdateUserForm): ResponseEntity<UserView> {
        val userView = service.update(form)
        return ResponseEntity.ok(userView)
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }
}