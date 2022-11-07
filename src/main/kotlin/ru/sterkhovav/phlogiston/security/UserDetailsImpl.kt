package ru.sterkhovav.phlogiston.security

import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority

import org.springframework.security.core.userdetails.UserDetails
import ru.sterkhovav.phlogiston.dao.models.User

class UserDetailsImpl(private val user: User) : UserDetails {

    override fun getAuthorities(): MutableCollection<out GrantedAuthority> {
        return mutableListOf(SimpleGrantedAuthority(user.role.name))
    }

    override fun getPassword(): String = user.password


    override fun getUsername(): String = user.username

    override fun isAccountNonExpired(): Boolean {
        return true
    }

    override fun isAccountNonLocked(): Boolean {
        return true
    }

    override fun isCredentialsNonExpired(): Boolean {
        return true
    }

    override fun isEnabled(): Boolean {
        return true
    }
}