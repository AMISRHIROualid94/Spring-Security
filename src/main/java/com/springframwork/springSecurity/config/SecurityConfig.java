package com.springframwork.springSecurity.config;

import com.springframwork.springSecurity.entity.User;
import com.springframwork.springSecurity.repository.UserRepository;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;

import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig{


    @Bean
    public WebSecurityCustomizer webSecurityCustomizer() throws Exception{
        return web -> {
            web.ignoring().requestMatchers(PathRequest.toH2Console());
        };
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{

      return   http
                .authorizeHttpRequests()
              .requestMatchers("/welcome").hasRole("USER")
              .requestMatchers("/","/**")
                        .permitAll()
              .and()
                        .formLogin()
                        .loginPage("/login")
                        .usernameParameter("username")
                        .passwordParameter("password")
                        .defaultSuccessUrl("/welcome",true)
              .and()
                        .build();


    }
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository){
        return username -> {
            User user = userRepository.findUserByUsername(username);
            if(user != null) return user;
            throw new UsernameNotFoundException("user "+ user +" Not found");
        };
    }
}
