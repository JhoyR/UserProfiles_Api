package api.kotlin.project.users.mapper

import api.kotlin.project.users.dto.UserView
import api.kotlin.project.users.model.User
import org.springframework.stereotype.Component

@Component
class UserViewMapper: Mapper<User, UserView> {
    override fun map (u: User): UserView{
       return UserView(
            id = u.id,
            name = u.name,
            email = u.email,
            status = u.status,
            date = u.date
        )
    }
}