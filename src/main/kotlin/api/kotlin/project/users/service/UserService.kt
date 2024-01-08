package api.kotlin.project.users.service

import api.kotlin.project.users.dto.NewUserForm
import api.kotlin.project.users.dto.UpdateUserForm
import api.kotlin.project.users.dto.UserView
import api.kotlin.project.users.exception.NotFoundException
import api.kotlin.project.users.mapper.UserFormMapper
import api.kotlin.project.users.mapper.UserViewMapper
import api.kotlin.project.users.repository.UserRepository
import org.springframework.data.domain.Page
import org.springframework.data.domain.Pageable
import org.springframework.stereotype.Service
import java.time.LocalDate

@Service

class UserService(
    private val repository: UserRepository,
    private val userViewMapper: UserViewMapper,
    private val userFormMapper: UserFormMapper,
    private val notFoundMessage: String = "Usuário não encontrado"
) {

    fun findAll(pagination: Pageable): Page<UserView> {
        return repository.findAll(pagination).map { u ->
            userViewMapper.map(u)
        }
    }

    fun findById(id: Long): UserView {
        val user = repository.findById(id)
            .orElseThrow { NotFoundException(notFoundMessage) }
        return userViewMapper.map(user)
    }

    fun create(form: NewUserForm): UserView {
        val user = userFormMapper.map(form)
        repository.save(user)
        return userViewMapper.map(user)
    }

    fun findByDateAdd(date: String?): Long {
        val usersOnDate = if (date == null) {
            repository.findAll().filter { u ->
                u.dateAdd == LocalDate.now()
            }
        } else {
            repository.findAll().filter { u ->
                u.dateAdd == LocalDate.parse(date)
            }
        }
        return usersOnDate.size.toLong()
    }

    fun update(form: UpdateUserForm): UserView {
        val user = repository.findById(form.id)
            .orElseThrow { NotFoundException(notFoundMessage) }

        user.name = form.name ?: user.name
        user.email = form.email ?: user.email
        user.password = form.password ?: user.password
        user.status = form.status ?: user.status

        return userViewMapper.map(user)
    }

    fun delete(id: Long) {
        repository.deleteById(id)
    }
}