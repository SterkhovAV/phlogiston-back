package ru.sterkhovav.phlogiston.dao.models

import com.vladmihalcea.hibernate.type.json.JsonBinaryType
import com.vladmihalcea.hibernate.type.json.JsonStringType
import org.hibernate.annotations.TypeDef
import org.hibernate.annotations.TypeDefs
import ru.sterkhovav.phlogiston.dto.RoleDto
import java.io.Serializable
import javax.persistence.*

@Entity
@Table(name = "phlogiston_u_roles")
@TypeDefs(
    TypeDef(name = "json", typeClass = JsonStringType::class),
    TypeDef(name = "jsonb", typeClass = JsonBinaryType::class)
)
data class Role(

    @field:Id
    @field:Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field:Column(name = "name", nullable = false, unique = true)
    val name: String,

    @field:Column(name = "description", nullable = false)
    val description: String
) : Serializable {
    fun toDto() = RoleDto(name, description)
}
