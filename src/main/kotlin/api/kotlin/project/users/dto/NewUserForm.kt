package api.kotlin.project.users.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

//Classe que possui os atributos recebidos do cliente
data class NewUserForm(
    @field:NotEmpty
    @field:Size(min = 3, max = 100)
    val name: String,

    @field:NotEmpty
    @field:Email
    @field:Size(min = 6, max = 100)
    val email: String,

    @field:NotEmpty
    @field:Size(min = 5, max = 100)
    val password: String
)