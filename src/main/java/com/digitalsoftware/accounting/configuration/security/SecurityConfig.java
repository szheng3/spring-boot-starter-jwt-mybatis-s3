package com.digitalsoftware.accounting.configuration.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity(debug = false)
//@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfig(@Qualifier("accountService") UserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }


    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(userDetailsService());
        return provider;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http
//            .formLogin().disable() // disable form authentication
//            .anonymous().disable() // disable anonymous user
//            .httpBasic().and()
//            // restricting access to authenticated users
//            .authorizeRequests().anyRequest().authenticated();
        http
            .authorizeRequests()
            .antMatchers("/api/image/**").permitAll()
            .antMatchers("/api/registerUser").permitAll()
//            .antMatchers("/api/oauth/token").permitAll()
//            .antMatchers("/gg/register").permitAll()
            .anyRequest().authenticated()
//            .and()
//            .formLogin().loginPage("/login").permitAll()
//            .and()
//            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout")).permitAll()
//            .and()
//            .exceptionHandling().accessDeniedPage("/access?error")
//            .and().headers().xssProtection().block(false).xssProtectionEnabled(false).and() // Default setting for Spring Boot to activate XSS Protection (dont fix!)
            .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        auth
//            .inMemoryAuthentication()
//            .withUser("user").password("{noop}password").roles("USER");
        auth
            .userDetailsService(userDetailsService)
            .passwordEncoder(passwordEncoder);
//        auth.inMemoryAuthentication() // creating user in memory
//            .withUser("user")
//            .password("password").roles("USER")
//            .and().withUser("admin")
//            .password("password").authorities("ROLE_ADMIN");

    }

    @Override
    @Bean
    public AuthenticationManager authenticationManagerBean() throws Exception {
        // provides the default AuthenticationManager as a Bean
        return super.authenticationManagerBean();
    }
}
