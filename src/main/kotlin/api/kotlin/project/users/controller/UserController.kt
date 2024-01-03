package api.kotlin.project.users.controller

import api.kotlin.project.users.dto.NewUserForm
import api.kotlin.project.users.dto.UserView
import api.kotlin.project.users.service.UserService
import jakarta.validation.Valid
import org.springframework.web.bind.annotation.*
import java.time.LocalDate

//Anotações
@RestController
@RequestMapping("/users")

class UserController(private val service: UserService) {

    @GetMapping
    //Retorna a lista com todos os usuários cadastrados
    fun allUsers(): List<UserView> {
        return service.allUsers()
    }

    //Retorna o usuário com base no id, por meio da uri "/{id}
    @GetMapping("/{id}")
    //Para que o spring saiba que o parâmetro id faz parte da uri - @PathVariable
    fun searchForId(@PathVariable id: Long): UserView {
        return service.searchForId(id)
    }

    //Retorna quantos usuários foram adicionados no dia informado (formato: YYYY-MM-DD)
    @GetMapping("/perDay/{date}")
    fun perDay(@PathVariable date: String): Int {
        return service.perDay(date)
    }
    //Retorna quantos usuários foram adicionados no dia atual
    @GetMapping("/perDay")
    fun perDayEmpty(): Int {
        return service.today()
    }

    //Rota para criar um novo usuário, considerando as validações propostas
    @PostMapping
    fun create(@RequestBody @Valid user: NewUserForm) {
        service.create(user)
    }

    //POST


    //DELETE
}