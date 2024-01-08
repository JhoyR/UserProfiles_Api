package api.kotlin.project.users.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.Size

data class NewUserForm(
    @field:NotEmpty(message = "Nome não pode estar em branco")
    @field:Size(min = 3, max = 100, message = "Nome deve conter entre 3 e 100 caracteres")
    val name: String,

    @field:NotEmpty(message = "Email não pode estar em branco")
    @field:Email
    @field:Size(min = 6, max = 100)
    val email: String,

    @field:NotEmpty(message = "Password não pode estar em branco")
    @field:Size(min = 5, max = 100, message = "Password deve conter entre 5 e 100 caracteres")
    val password: String
)