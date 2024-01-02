package api.kotlin.project.users.dto

//Classe que possui os atributos
data class UserDto (
    val name: String,
    val email: String,
    val password: String
)