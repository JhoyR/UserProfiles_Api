package api.kotlin.project.users.controller

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.delete
import org.springframework.test.web.servlet.get
import org.springframework.test.web.servlet.setup.MockMvcBuilders
import org.springframework.web.context.WebApplicationContext

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class UserControllerTest {

    @Autowired
    private lateinit var webApplicationContext: WebApplicationContext

    private lateinit var mockMvc: MockMvc

    companion object {
        private const val URI = "/users"
        private const val URI_WITH_DATE = "/users?2024-01-01"
        private const val URI_WITH_PARAM = "/users/1"
    }

    @BeforeEach
    fun setup() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
            .build()
    }

    @Test
    fun `Deve retornar codigo 200 quando chamar usuarios`() {
        mockMvc.get(URI).andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `Deve retornar codigo 200 quando chamar usuarios pelo id`() {
        mockMvc.get(URI_WITH_PARAM).andExpect { status { is2xxSuccessful() } }
    }

    @Test
    fun `Deve retornar codigo 404 not found quando chamar um usuario por id inexistente`() {
        mockMvc.get("$URI/3").andExpect { status { isNotFound() } }
    }

    @Test
    fun `Deve retornar codigo 204 no content quando chamar request delete usuario`() {
        mockMvc.delete(URI_WITH_PARAM).andExpect { status { isNoContent() } }
    }
}