package api.kotlin.project.users.controller

import api.kotlin.project.users.dto.UserDto
import api.kotlin.project.users.model.User
import api.kotlin.project.users.service.UserService
import org.springframework.web.bind.annotation.*

//Anotações
@RestController
@RequestMapping("/users")

class UserController(private val service: UserService) {

    @GetMapping
    //Retorna a lista com todos os usuários cadastrados
    fun allUsers(): List<User> {
        return service.allUsers()
    }

    //Retorna o usuário com base no id, por meio da uri "/{id}
    @GetMapping("/{id}")
    //Para que o spring saiba que o parâmetro id faz parte da uri - @PathVariable
    fun searchForId(@PathVariable id: Long): User {
    return service.searchForId(id)
    }

    //Retorna quantos usuários foram adicionados no dia informado
    //Se chamado sem parâmetro, retorna a média de usuários adicionados por dia
//    @GetMapping("/perDay/{date}")
//    fun addedPerDay(@PathVariable @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) date: LocalDate?): User {
//        return service.addedPerDay(date)
//    }

    @PostMapping
    fun toAdd(@RequestBody user: UserDto){
        service.toAdd(user)
    }
}