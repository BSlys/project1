package com.sda.werehouse.unit303.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    public JdbcTemplate jdbcTemplate;

    @Autowired
    public PasswordEncoder passwordEncoder;

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                .antMatchers("/").hasAnyAuthority("ROLE_KEEPER", "ROLE_PRIVATE", "ROLE_GENERAL")
                .antMatchers("/index/**").hasAnyAuthority("ROLE_KEEPER", "ROLE_PRIVATE", "ROLE_GENERAL")
                .antMatchers("/adduser").hasAnyAuthority("ROLE_KEEPER", "ROLE_GENERAL", "ROLE_PRIVATE")
                .antMatchers("/adduser").hasAnyAuthority("ROLE_KEEPER", "ROLE_GENERAL", "ROLE_PRIVATE")
                .antMatchers("/deleteUser").hasAnyAuthority("ROLE_KEEPER", "ROLE_GENERAL")
                .antMatchers("/myOrder").hasAnyAuthority("ROLE_KEEPER", "ROLE_GENERAL", "ROLE_PRIVATE")
                .antMatchers("/inventory").hasAnyAuthority("ROLE_KEEPER", "ROLE_GENERAL")
                .antMatchers("/deleteUser").hasAnyAuthority("ROLE_KEEPER", "ROLE_GENERAL")
                .antMatchers("/deleteItem").hasAnyAuthority("ROLE_KEEPER")
                .antMatchers("/addItem").hasAnyAuthority("ROLE_KEEPER")
                .antMatchers("/acceptOrder").hasAnyAuthority("ROLE_KEEPER")
                .antMatchers("/deleteOrderI").hasAnyAuthority("ROLE_KEEPER")
                .anyRequest().permitAll()
                .and()
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                .exceptionHandling()
                .accessDeniedPage("/fallback?message=BrakUprawnien")
                .and()
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/login-process")
                .failureUrl("/fallback?message=BladLogowania")
                .defaultSuccessUrl("/index")
                .and()
                .logout().logoutSuccessUrl("/login");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {

        auth.inMemoryAuthentication()
                .withUser("user").password(passwordEncoder.encode("password"))
                .roles("USER")
                .and().withUser("superuser").password(passwordEncoder.encode("password"))
                .roles("ADMIN", "KEEPER");


        auth.jdbcAuthentication().usersByUsernameQuery("SELECT u.name, u.password, 1 FROM user u WHERE u.name=?")
                .authoritiesByUsernameQuery("SELECT u.name, u.roles, 1 FROM user u WHERE u.name=?")
                .dataSource(jdbcTemplate.getDataSource()).passwordEncoder(passwordEncoder);
        System.out.println(auth.toString());
    }
}
