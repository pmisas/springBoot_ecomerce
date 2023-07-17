package com.test.project.repository;


import com.test.project.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);
}

