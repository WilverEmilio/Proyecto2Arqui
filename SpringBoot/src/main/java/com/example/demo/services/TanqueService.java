package com.example.demo.services;

import com.example.demo.models.Tanque;
import com.example.demo.repositories.TanqueRepository;
import com.example.demo.request.Request;
import com.example.demo.request.RequestDTO;
import com.example.demo.response.ResponseDTO;
import com.example.demo.response.TanqueResponse;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class TanqueService {

    @Autowired
    TanqueRepository tanqueRepository;

    //Obtener todos los tanques
    public ArrayList<Tanque> obtenerTodoslosTanques(){
        return (ArrayList<Tanque>) tanqueRepository.findAll();
    }

    //obtener un tanque por id
    public Optional<Tanque> obtenerIDTanque(Integer Id){
        if(tanqueRepository.existsById(Id)){
            return tanqueRepository.findById(Id);
        }
        else {
            return Optional.empty();
        }
    }

    //Guardar un tanque
    public ResponseEntity<ResponseDTO> guardarTanque(RequestDTO requestDTO){
        Tanque tanque = tanqueRepository.save(requestDTO.getRequest().getTanque());
        //Llenar una respuesta al cliente
        TanqueResponse tanqueResponse = new TanqueResponse();
        tanqueResponse.setCapacidad(tanque.getCapacidad());
        tanqueResponse.setNivel_actual(tanque.getNivel_actual());
        tanqueResponse.setUbicacion(tanque.getUbicacion());
        tanqueResponse.setTipo_gasolina(tanque.getTipo_gasolina());
        tanqueResponse.setCreate_ad(tanque.getCreate_ad());
        tanqueResponse.setUpdate_ad(tanque.getUpdate_ad());

        //Dar una respuesta al cliente
        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("Tanque Guardaro");
        responseDTO.setTanque(tanqueResponse);

        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //Actualizar un tanque
    public ResponseEntity<ResponseDTO> actualizarTanque(RequestDTO requestDTO){
        tanqueRepository.updateCapacidadTanque(requestDTO.getRequest().getTanque().getCapacidad(), requestDTO.getRequest().getTanque().getId());
        tanqueRepository.updateNivel_actualTanque(requestDTO.getRequest().getTanque().getNivel_actual(), requestDTO.getRequest().getTanque().getId());
        tanqueRepository.updateUbicacionTanque(requestDTO.getRequest().getTanque().getUbicacion(), requestDTO.getRequest().getTanque().getId());
        tanqueRepository.updateTipo_gasolinaTanque(requestDTO.getRequest().getTanque().getTipo_gasolina(), requestDTO.getRequest().getTanque().getId());

        //Llenr una respuesta al cliente
        TanqueResponse tanqueResponse = new TanqueResponse();
        tanqueResponse.setCapacidad(requestDTO.getRequest().getTanque().getCapacidad());
        tanqueResponse.setNivel_actual(requestDTO.getRequest().getTanque().getNivel_actual());
        tanqueResponse.setUbicacion(requestDTO.getRequest().getTanque().getUbicacion());
        tanqueResponse.setTipo_gasolina(requestDTO.getRequest().getTanque().getTipo_gasolina());

        ResponseDTO responseDTO = new ResponseDTO();
        responseDTO.setResponse("Se actualizo el tanque");
        responseDTO.setTanque(tanqueResponse);

        return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
    }

    //Eliminar un tanque
    public ResponseEntity<ResponseDTO> EliminarTanque(RequestDTO requestDTO){
        ResponseDTO responseDTO = new ResponseDTO();
        Integer id_tanque  = requestDTO.getRequest().getTanque().getId();
        TanqueResponse tanqueResponse = new TanqueResponse();
        if(tanqueRepository.existsById(id_tanque)){
            tanqueRepository.deleteById(id_tanque);
            responseDTO.setResponse("Se elimino el tanque");
            responseDTO.setTanque(tanqueResponse);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.OK);
        }
        else {
            responseDTO.setResponse("No existe el tanque");
            responseDTO.setTanque(tanqueResponse);
            return new ResponseEntity<ResponseDTO>(responseDTO, HttpStatus.NOT_FOUND);
        }
    }
}
