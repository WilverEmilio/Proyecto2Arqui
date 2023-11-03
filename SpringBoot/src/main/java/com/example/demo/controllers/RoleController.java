package com.example.demo.controllers;

import com.example.demo.models.Role;
import com.example.demo.models.User;
import com.example.demo.services.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RoleController {
    @Autowired
    RoleService roleService;

    @GetMapping("/role")
    public ArrayList<Role> obtenerRoles(){
        return roleService.obtenerRoles();
    }

    @PostMapping("/role")
    public Role guardarRol(@RequestBody Role role){
        return this.roleService.guardarRol(role);
    }

    @GetMapping("/role/{id}")
    public Optional<Role> obtenerId(@PathVariable("id") Integer id){
        return this.roleService.obtenerId(id);
    }
}
