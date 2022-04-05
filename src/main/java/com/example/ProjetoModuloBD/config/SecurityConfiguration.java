package com.example.ProjetoModuloBD.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;

import javax.sql.DataSource;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.httpBasic().and().authorizeRequests().antMatchers("/produto/**").authenticated()
                .antMatchers("/compra/**").authenticated()
                .antMatchers("/user/**").authenticated();
    }

  @Bean
    public UserDetailsManager users(DataSource dataSource){
        JdbcUserDetailsManager user = new JdbcUserDetailsManager(dataSource);
        return user;
    }
}
