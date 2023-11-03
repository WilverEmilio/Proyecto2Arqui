package com.example.demo.controllers;

import com.example.demo.models.Tanque;
import com.example.demo.request.RequestDTO;
import com.example.demo.response.ResponseDTO;
import com.example.demo.services.TanqueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/apiSPTanque")
public class TanqueController {
    @Autowired
    TanqueService tanqueService;

    //Ruta para obtener todoslos tanques
    @GetMapping("/TodosTanques")
    public ArrayList<Tanque> obtenerTodosTanques(){
        return tanqueService.obtenerTodoslosTanques();
    }

    //Ruta para obtener un tanque por id
    @GetMapping("/IdTanque/{id}")
    public Optional<Tanque> obtenerIDTanque(@PathVariable("id") Integer id){
        return tanqueService.obtenerIDTanque(id);
    }

    //Ruta para crear datos
    @PostMapping("/crearTanque")
    public ResponseEntity<ResponseDTO> crearTanque(@RequestBody RequestDTO requestDTO){
        return tanqueService.guardarTanque(requestDTO);
    }

    //Ruta para actulizar los datos de tanques
    @PutMapping("/actualizarTanque")
    public ResponseEntity<ResponseDTO> actualizarTanque(@RequestBody RequestDTO requestDTO){
        return tanqueService.actualizarTanque(requestDTO);
    }

    //Ruta para eliminar un tanque
    @DeleteMapping("/eliminarTanque")
    public ResponseEntity<ResponseDTO> eliminarTanque(@RequestBody RequestDTO requestDTO){
        return tanqueService.EliminarTanque(requestDTO);
    }
}
