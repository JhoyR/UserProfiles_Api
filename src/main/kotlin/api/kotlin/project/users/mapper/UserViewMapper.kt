package api.kotlin.project.users.mapper

import api.kotlin.project.users.dto.UserView
import api.kotlin.project.users.model.UserProfile
import org.springframework.stereotype.Component

@Component
class UserViewMapper : Mapper<UserProfile, UserView> {
    override fun map(t: UserProfile): UserView {
        return UserView(
            id = t.id,
            name = t.name,
            email = t.email,
            status = t.status,
            dateAdd = t.dateAdd
        )
    }
}