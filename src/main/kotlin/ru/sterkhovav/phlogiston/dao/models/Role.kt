package ru.sterkhovav.phlogiston.dao.models

import javax.persistence.*

@Entity
@Table(name = "phlogiston_u_roles")
data class Role(
    @field:Id
    @field:Column(name = "id", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    val id: Long,

    @field:Column(name = "name", nullable = false, unique = true)
    val name: String,

    @field:Column(name = "description", nullable = false)
    val description: String
)
