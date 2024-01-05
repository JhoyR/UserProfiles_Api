package api.kotlin.project.users.controller

import api.kotlin.project.users.dto.NewUserForm
import api.kotlin.project.users.dto.UpdateUserForm
import api.kotlin.project.users.dto.UserView
import api.kotlin.project.users.service.UserService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.cache.annotation.CacheEvict
import org.springframework.cache.annotation.Cacheable
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.data.web.PageableDefault
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import org.springframework.web.util.UriBuilder
import org.springframework.web.util.UriComponentsBuilder
import java.time.LocalDate

//Anotações
@RestController
@RequestMapping("/users")

class UserController(private val service: UserService) {

    @GetMapping
    @Cacheable("userList")
    //Retorna a lista com todos os usuários cadastrados
    fun findAll(
        @PageableDefault(size = 10) pagination: Pageable
    ): Page<UserView> {
        return service.findAll(pagination)
    }

    //Retorna o usuário com base no id, por meio da uri "/{id}
    @GetMapping("/{id}")
    //Para que o spring saiba que o parâmetro {id} faz parte da uri - @PathVariable
    fun searchForId(@PathVariable id: Long): UserView {
        return service.searchForId(id)
    }

    //Retorna quantos usuários foram adicionados no dia informado (formato: YYYY-MM-DD) - '/byDay?2024-01-01'
    //Quando vazio, retorna quantos usuários foram adicionados no dia atual
    @GetMapping("/byDay")
    fun perDay(@RequestParam(required = false) date: String?): Long {
        return service.byDay(date)
    }

    //Rota para criar um novo usuário, considerando as validações propostas
    @PostMapping
    @Transactional
    @CacheEvict(value = ["userList"], allEntries = true) //Limpa todos os caches "userList"
    fun create(
        @RequestBody @Valid
        user: NewUserForm,
        uriBuilder: UriComponentsBuilder
    ): ResponseEntity<UserView> {
        val userView = service.create(user)
        val uri = uriBuilder.path("/users/${userView.id}").build().toUri()
        return ResponseEntity.created(uri).body(userView)
    }

    //Rota para atualizar um usuário existente
    @PutMapping
    @Transactional
    @CacheEvict(value = ["userList"], allEntries = true)
    fun update(@RequestBody @Valid form: UpdateUserForm): ResponseEntity<UserView> {
        val userView = service.update(form)
        return ResponseEntity.ok(userView)
    }

    //DELETE
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    @CacheEvict(value = ["userList"], allEntries = true)
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }
}