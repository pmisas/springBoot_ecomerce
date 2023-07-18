package com.test.project.security.service;

import com.test.project.security.entity.Rol;
import com.test.project.security.enums.RolName;
import com.test.project.security.repository.IRolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    IRolRepository rolRepository;

    public Optional<Rol> getByRolName(RolName rolName) {
        return rolRepository.findByName(rolName);
    }

}
