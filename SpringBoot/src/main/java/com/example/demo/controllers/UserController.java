package com.example.demo.controllers;

import com.example.demo.models.User;
import com.example.demo.request.RequestDTO;
import com.example.demo.response.ResponseDTO;
import com.example.demo.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping("/user")
    public ArrayList<User> obtenerUsuarios(){
        return userService.obtenerUsuarios();
    }

    @GetMapping("/user/{id}")
    public Optional<User> obtenerId(@PathVariable("id") Integer id){
        return this.userService.obtenerId(id);
    }

    @PostMapping("/user")
    public ResponseEntity<ResponseDTO> guardarUser(@RequestBody RequestDTO requestDTO){
        return this.userService.guardarUsuario(requestDTO);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Object> eliminarUser(@PathVariable("id") Integer id){
        Optional<User> userOptional = userService.obtenerId(id);
        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("No se encontró");
        }
        //Aqui hacer la eliminación
        return ResponseEntity.status(HttpStatus.OK).body(userService.eliminarUsuario(id));
    }

    @PostMapping("/update/{id}/{id_role}")
    public ResponseEntity<Object> asignarRol(@PathVariable("id") Integer id, @PathVariable("id_role") Integer id_role){
        Optional<User> userOptional = userService.obtenerId(id);
        if(userOptional.isEmpty()){
            return ResponseEntity.status(HttpStatus.OK).body("No se encontró");
        }
        return ResponseEntity.status(HttpStatus.OK).body(userService.actualizarRol(id, id_role));
    }
}
