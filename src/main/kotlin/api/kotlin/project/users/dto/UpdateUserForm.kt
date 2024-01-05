package api.kotlin.project.users.dto

import api.kotlin.project.users.model.StatusUser
import jakarta.annotation.Nullable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UpdateUserForm (
    @field:NotNull
    val id: Long,

    @field:NotEmpty
    @field:Size(min = 3, max = 100)
    val name: String,

    @field:NotEmpty
    @field:Email
    @field:Size(min = 6, max = 100)
    val email: String,

    @field:NotEmpty
    @field:Size(min = 5, max = 100)
    val password: String,

    @field:Nullable //OPCIONAL
    val status: StatusUser? = null,
)