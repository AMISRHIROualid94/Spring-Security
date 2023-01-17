package com.springframwork.springSecurity.repositories;

import com.springframwork.springSecurity.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findUserByUsername(String username);
}
