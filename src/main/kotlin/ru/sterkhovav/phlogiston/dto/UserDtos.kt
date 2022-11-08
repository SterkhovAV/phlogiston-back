package ru.sterkhovav.phlogiston.dto

import org.springframework.beans.factory.annotation.Autowired
import ru.sterkhovav.phlogiston.dao.models.Role
import ru.sterkhovav.phlogiston.dao.models.User
import ru.sterkhovav.phlogiston.dao.repository.RoleRepository
import ru.sterkhovav.phlogiston.service.RoleServiceImpl
import java.time.OffsetDateTime

data class UserLoginRequest(
    val username: String,
    var password: String,
)

data class UserRegistrationRequestDto(
    val username: String,
    var password: String,
    val email: String,
    val lastName: String,
    val firstName: String,
    val middleName: String?,
    val organisation: String?,
    val position: String?,
    val phone: String?,
    val roleId: Int,
) {



    fun toEntity(role:Role) = User(
        username = this.username,
        active = true,
        password = this.password,
        email = this.email,
        lastName = this.lastName,
        firstName = this.firstName,
        middleName = this.middleName,
        organisation = this.organisation,
        position = this.position,
        phone = this.phone,
        createDate = OffsetDateTime.now(),
        lastTimeUpdatePassword = OffsetDateTime.now(),
        role = role,
    )
}


data class UserDto(
    val username: String,
    val active: Boolean,
    val email: String,
    val lastName: String,
    val firstName: String,
    val middleName: String? = null,
    val organisation: String? = null,
    val position: String? = null,
    val phone: String? = null,
    val createDate: OffsetDateTime,
    val lastTimeUpdatePassword: OffsetDateTime,
    val role: RoleDto
)












