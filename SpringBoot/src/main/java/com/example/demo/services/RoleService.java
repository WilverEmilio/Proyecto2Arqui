package com.example.demo.services;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.repositories.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class RoleService {
    @Autowired
    RoleRepository roleRepository;

    public ArrayList<Role> obtenerRoles(){
        return (ArrayList<Role>) roleRepository.findAll();
    }

    public Role guardarRol(Role role){
        return roleRepository.save(role);
    }

    public Optional<Role> obtenerId(Integer id){
        return roleRepository.findById(id);
    }
}
