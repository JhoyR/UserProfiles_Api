package api.kotlin.project.users.controller

import api.kotlin.project.users.dto.NewUserForm
import api.kotlin.project.users.dto.UpdateUserForm
import api.kotlin.project.users.dto.UserView
import api.kotlin.project.users.service.UserService
import jakarta.transaction.Transactional
import jakarta.validation.Valid
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
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
    //Retorna a lista com todos os usuários cadastrados
    fun findAll(
        pagination: Pageable
    ): Page<UserView> {
        return service.findAll(pagination)
    }

    //Retorna o usuário com base no id, por meio da uri "/{id}
    @GetMapping("/{id}")
    //Para que o spring saiba que o parâmetro id faz parte da uri - @PathVariable
    fun searchForId(@PathVariable id: Long): UserView {
        return service.searchForId(id)
    }

    //Retorna quantos usuários foram adicionados no dia informado (formato: YYYY-MM-DD)
    //Quando vazio, retorna quantos usuários foram adicionados no dia atual
    @GetMapping("/byDay")
    fun perDay(@RequestParam(required = false) date: String?): Long {
        return service.byDay(date)
    }

    //Rota para criar um novo usuário, considerando as validações propostas
    @PostMapping    // RETORNA 201, LEVA CABEÇALHO E LOCATION, E O RECURSO NO CORPO DA RESPOSTA
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

    //Rota para atualizar um usuário existente
    @PutMapping      //RETORNA 200, E LEVA O RECURSO NO CORPO DA RESPOSTA ATUALIZADO
    @Transactional
    fun update(@RequestBody @Valid form: UpdateUserForm): ResponseEntity<UserView> {
        val userView = service.update(form)
        return ResponseEntity.ok(userView)
    }

    //DELETE
    @DeleteMapping("/{id}")     //RETORNA CODIGO 204
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @Transactional
    fun delete(@PathVariable id: Long) {
        service.delete(id)
    }
}