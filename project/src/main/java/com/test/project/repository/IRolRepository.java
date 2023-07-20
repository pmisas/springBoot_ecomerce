package com.test.project.repository;

import com.test.project.entity.Rol;
import com.test.project.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface IRolRepository extends JpaRepository<Rol, Long> {
    Set<Rol> findByName(RolName name);
}

