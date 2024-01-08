package api.kotlin.project.users.dto

import api.kotlin.project.users.model.StatusUser
import jakarta.annotation.Nullable
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Size

data class UpdateUserForm(
    @field:NotNull
    val id: Long,

    @field:Nullable
    @field:Size(min = 3, max = 100)
    val name: String? = null,

    @field:Nullable
    @field:Email
    @field:Size(min = 6, max = 100)
    val email: String? = null,

    @field:Nullable
    @field:Size(min = 5, max = 100)
    val password: String? = null,

    @field:Nullable
    val status: StatusUser? = null,
)