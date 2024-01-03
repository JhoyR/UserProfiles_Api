package api.kotlin.project.users.mapper

import api.kotlin.project.users.dto.NewUserForm
import api.kotlin.project.users.model.User
import org.springframework.stereotype.Component

@Component
class UserFormMapper: Mapper<NewUserForm, User> {
    override fun map(t: NewUserForm): User {
       return User(
            name = t.name,
            email = t.email,
            password = t.password
        )
    }
}
