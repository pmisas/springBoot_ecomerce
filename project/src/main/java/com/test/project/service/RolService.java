package com.test.project.service;

import com.test.project.entity.Rol;
import com.test.project.enums.RolName;
import com.test.project.repository.IRolRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    IRolRepository rolRepository;

    /*
    public Rol getByRolName(RolName rolName) {
        return rolRepository.findByRolName(rolName);
    }
     */

    public void save(Rol rol){
        rolRepository.save(rol);
    }

}
