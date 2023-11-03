package com.example.demo.services;


import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.request.RequestDTO;
import com.example.demo.response.ResponseDTO;
import com.example.demo.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    UserRepository userRepository;

    public ArrayList<User> obtenerUsuarios(){
        return (ArrayList<User>) userRepository.findAll();
    }

    public ResponseEntity<ResponseDTO> guardarUsuario(RequestDTO requestDTO){
        //Fecha actual
        LocalDateTime localDateTime = LocalDateTime.now();
        ZonedDateTime zdt = ZonedDateTime.of(localDateTime, ZoneId.systemDefault());
        long date = zdt.toInstant().toEpochMilli();

        //setear el ts_insert
        requestDTO.getRequest().getUser().setTs_insert(localDateTime);
        //requestDTO.getRequest().getUser().setPassword(password);
        User user = userRepository.save(requestDTO.getRequest().getUser());

        //Configurando la respuesta del usuario
        UserResponse userResponse = new UserResponse();
        userResponse.setId(user.getId());
        userResponse.setName(user.getName());
        userResponse.setUsername(user.getUsername());
        userResponse.setStatus(user.getStatus());
        userResponse.setTs_insert(date); //No se setea un date sino que se vuelve Long
        userResponse.setId_role(user.getId_role());

        //Configurando el objeto DTO para tener una respuesta OK
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("OK");
        responseDTO.setUser(userResponse);
        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    public Optional<User> obtenerId(Integer id){
        return userRepository.findById(id);
    }

    //Aqui hacer la eliminacion logica
    public String eliminarUsuario(Integer id){
        userRepository.updateStatus(id);
        return "ok";
    }

    public String actualizarRol(Integer id, Integer id_role){
        userRepository.updateIdRole(id, id_role);
        return "ok";
    }
}
