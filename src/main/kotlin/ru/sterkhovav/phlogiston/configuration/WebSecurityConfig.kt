package ru.sterkhovav.phlogiston.configuration

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpStatus
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.web.authentication.HttpStatusEntryPoint
import ru.sterkhovav.phlogiston.controllers.AUTH_BASE_API
import ru.sterkhovav.phlogiston.controllers.BASE_API
import ru.sterkhovav.phlogiston.security.CustomAuthenticationFailureHandler
import ru.sterkhovav.phlogiston.security.CustomAuthenticationSuccessHandler
import ru.sterkhovav.phlogiston.service.UserDetailsServiceImpl
import ru.sterkhovav.phlogiston.service.UserServiceImpl


@Configuration
@EnableWebSecurity
class WebSecurityConfig(
    val userDetailsServiceImpl: UserDetailsServiceImpl,
) : WebSecurityConfigurerAdapter() {

    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.csrf().disable() //TODO включить
            .authorizeRequests()
            .antMatchers("/", "/js/**", "/js/**", "$BASE_API/")
            .permitAll()
            .antMatchers("$AUTH_BASE_API/registration", "$AUTH_BASE_API/activate")
            .permitAll()
            .anyRequest().authenticated()
            .and().exceptionHandling().authenticationEntryPoint(HttpStatusEntryPoint(HttpStatus.UNAUTHORIZED))
            .and().formLogin()
            .successHandler(CustomAuthenticationSuccessHandler(userDetailsServiceImpl))
            .failureHandler(CustomAuthenticationFailureHandler(userDetailsServiceImpl))
            .loginProcessingUrl("${AUTH_BASE_API}/login")
            .and().logout().logoutUrl("${AUTH_BASE_API}/logout")
            .logoutSuccessUrl("/")
            .deleteCookies("JSESSIONID")
    }

    @Throws(Exception::class)
    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsServiceImpl)
    }

    @Bean
    fun bCryptPasswordEncoder(): BCryptPasswordEncoder {
        return BCryptPasswordEncoder()
    }
}