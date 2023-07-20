package com.test.project.repository;

import com.test.project.entity.Rol;
import com.test.project.enums.RolName;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IRolRepository extends JpaRepository<Rol, Long> {
    //Rol findByRolName(RolName rolName);
}

