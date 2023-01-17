package com.springframwork.springSecurity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .formLogin()
                .loginPage("/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public UserDetailsService userDetailsService(PasswordEncoder encoder){
       /* return username -> {
            User user = userRepository.findUserByUserName(username);
            if(user != null) return user;
            throw new UsernameNotFoundException("user" + user + " Not found");
        };*/
       List<UserDetails> userDetailsList = new ArrayList<>();
        userDetailsList.add(new User("dodo",encoder.encode("passworddodo"), Arrays.asList(
                new SimpleGrantedAuthority("ROLE_USER")
        )));
        userDetailsList.add(new User("fofo",encoder.encode("passwordfofo"), Arrays.asList(
                new SimpleGrantedAuthority("ROLE_USER")
        )));
        return new InMemoryUserDetailsManager(userDetailsList);
    }
}
