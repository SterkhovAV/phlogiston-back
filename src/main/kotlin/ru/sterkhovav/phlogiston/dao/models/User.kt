package ru.sterkhovav.phlogiston.dao.models

import ru.sterkhovav.phlogiston.dto.UserDto
import java.time.OffsetDateTime
import java.util.*
import javax.persistence.*

@Entity
@Table(name = "phlogiston_users")
data class User(

    @field:Id
    @field:Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long? = null,

    @field:Column(name = "username", nullable = false, unique = true)
    var username: String,

    @field:Column(name = "active", nullable = false)
    var active: Boolean = true,

    @field:Column(name = "password", nullable = false)
    var password: String,

    @field:Column(name = "email", nullable = false)
    var email: String,

    @field:Column(name = "last_name", nullable = false)
    var lastName: String,

    @field:Column(name = "first_name", nullable = false)
    var firstName: String,

    @field:Column(name = "middle_name")
    var middleName: String?,

    @field:Column(name = "organisation")
    var organisation: String?,

    @field:Column(name = "position")
    var position: String?,

    @field:Column(name = "phone")
    var phone: String?,

    @field:Column(name = "create_date", nullable = false)
    val createDate: OffsetDateTime,

    @field:Column(name = "last_time_update_password", nullable = false)
    var lastTimeUpdatePassword: OffsetDateTime,

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    val role: Role,

    @field:Column(name = "failed_login_counter", nullable = false)
    var failedLoginCounter: Int = 0,

    @field:Column(name = "invalid_password_time")
    var invalidPasswordTime: OffsetDateTime? = null,

    @field:Column(name = "uuid")
    var uuid: UUID

    ) {
    fun toDto() = UserDto(
        username, active, email, lastName, firstName, middleName, organisation, position,
        phone, createDate, lastTimeUpdatePassword, role.toDto()
    )
}




