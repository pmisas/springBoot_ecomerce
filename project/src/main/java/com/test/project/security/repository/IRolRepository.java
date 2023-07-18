package com.test.project.security.repository;

import com.test.project.security.entity.Rol;
import com.test.project.security.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolRepository extends JpaRepository<Rol, Long> {
    Optional<Rol> findByName(RolName rolName);
}
