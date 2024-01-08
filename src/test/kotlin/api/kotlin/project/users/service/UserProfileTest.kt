
import api.kotlin.project.users.dto.UserView
import api.kotlin.project.users.exception.NotFoundException
import api.kotlin.project.users.mapper.UserFormMapper
import api.kotlin.project.users.mapper.UserViewMapper
import api.kotlin.project.users.model.UserProfileTest
import api.kotlin.project.users.model.UserProfileViewTest
import api.kotlin.project.users.repository.UserRepository
import api.kotlin.project.users.service.UserService
import io.mockk.every
import io.mockk.mockk
import io.mockk.verify
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageImpl
import org.springframework.data.domain.Pageable
import java.time.LocalDate
import java.util.*

class UserServiceTest {
    private val user = UserProfileTest.build()
    private val userView = UserProfileViewTest.build()

    private val userRepository: UserRepository = mockk()
    private val userViewMapper: UserViewMapper = mockk()
    private val userFormMapper: UserFormMapper = mockk()
    private val userService = UserService(userRepository, userViewMapper, userFormMapper)
    private val pageable: Pageable = mockk()

    private val users = PageImpl(listOf(UserProfileTest.build()))

    @Test
    fun `findAll deve retornar uma lista de usuários mapeados`() {
        every { userRepository.findAll(pageable) } returns users
        every { userViewMapper.map(any()) } returns userView

        val result: Page<UserView> = userService.findAll(pageable)

        verify(exactly = 1) { userRepository.findAll(pageable) }
        verify(exactly = 1) { userViewMapper.map(any()) }

        assertThat(result).isEqualTo(users.map { userViewMapper.map(it) })
    }

    @Test
    fun `findByDateAdd deve retornar zero quando não há usuários na data fornecida`() {
        val dateString = "2024-01-01"
        val date = LocalDate.parse(dateString)

        every { userRepository.findByDateAdd(date) } returns 0
        every { userRepository.findAll() } returns emptyList()

        val result = userService.findByDateAdd(date.toString())

        verify(exactly = 1) { userRepository.findAll() }

        assertThat(result).isEqualTo(0)
    }

    @Test
    fun `findByDateAdd deve retornar a quantidade de usuários do dia atual quando a data é nula`() {
        every { userRepository.findAll() } returns listOf(user)

        val result = userService.findByDateAdd(null)

        verify(exactly = 1) { userRepository.findAll() }

        assertThat(result).isEqualTo(1L)
    }

    @Test
    fun `findById deve retornar um usuário pelo id`() {
        val id = 2L
        every { userRepository.findById(id) } returns Optional.of(user)
        every { userViewMapper.map(any()) } returns UserProfileViewTest.build()

        val result: UserView = userService.findById(id)

        assertThat(result).isNotNull()
        verify(exactly = 1) { userRepository.findById(id) }
        verify(exactly = 1) { userViewMapper.map(any()) }
    }

    @Test
    fun `deve lançar NotFoundException se não encontrar usuário por id`() {
        every { userRepository.findById(any()) } returns Optional.empty()

        val actual = assertThrows<NotFoundException> {
            userService.findById(2)
        }

        assertThat(actual.message).isEqualTo("Usuário não encontrado")
    }
}
