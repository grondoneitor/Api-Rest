package com.api.facu.api_rest_facu.controllers;

import com.api.facu.api_rest_facu.Models.dto.ClienteDto;
import com.api.facu.api_rest_facu.Models.entity.Cliente;
import com.api.facu.api_rest_facu.servicio.IClienteServicio;
import com.api.facu.api_rest_facu.servicio.implementaciones.ClienteIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.api.facu.api_rest_facu.Models.payload.response;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ClienteControler {

    @Autowired
    private IClienteServicio clienteIMPL;

    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  create( @RequestBody ClienteDto clienteDto) {
        try{
            Cliente clienteCreate = clienteIMPL.save(clienteDto);
            ClienteDto cliente =ClienteDto.builder()
                    .idCliente(clienteCreate.getIdCliente())
                    .nombre(clienteCreate.getNombre())
                    .apellido(clienteCreate.getApellido())
                    .correo(clienteCreate.getCorreo())
                    .fechaRegistro(clienteCreate.getFechaRegistro())
                    .build();
            return new ResponseEntity<>(response.builder().mensaje("Guardado Correctamente").objecto(cliente).build(), HttpStatus.CREATED);
        }catch (DataAccessException ex){

            return new ResponseEntity<>(response.builder().mensaje(ex.getMessage()).objecto(null),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }

    @PutMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  update(@RequestBody ClienteDto clienteDto) {
        try{
            Cliente clienteUpdate = clienteIMPL.save(clienteDto);
            ClienteDto cliente =ClienteDto.builder()
                    .idCliente(clienteUpdate.getIdCliente())
                    .nombre(clienteUpdate.getNombre())
                    .apellido(clienteUpdate.getApellido())
                    .correo(clienteUpdate.getCorreo())
                    .fechaRegistro(clienteUpdate.getFechaRegistro())
                    .build();
           return new ResponseEntity<>(response.builder().mensaje("Actualizado Correctamente").objecto(cliente).build(), HttpStatus.CREATED);
        }catch (DataAccessException ex){

            return new ResponseEntity<>(response.builder().mensaje(ex.getMessage()).objecto(null),HttpStatus.INTERNAL_SERVER_ERROR);

        }


    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {


        try{
            Cliente nuevoCliente = clienteIMPL.finById(id);
            clienteIMPL.delete(nuevoCliente);

            return new ResponseEntity<>(nuevoCliente, HttpStatus.NO_CONTENT);

        }catch (DataAccessException ex){
            return new ResponseEntity<>(response.builder()
                                                .mensaje(ex.getMessage())
                                                .objecto(null)
                                                .build()
                                                , HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<?> showById(@PathVariable Integer id){

        try{
            Cliente clienteById = clienteIMPL.finById(id);

            ClienteDto cliente =ClienteDto.builder()
                    .idCliente(clienteById.getIdCliente())
                    .nombre(clienteById.getNombre())
                    .apellido(clienteById.getApellido())
                    .correo(clienteById.getCorreo())
                    .fechaRegistro(clienteById.getFechaRegistro())
                    .build();
            return new ResponseEntity<>(response.builder().mensaje("Buscado Correctamente").objecto(cliente).build(), HttpStatus.OK);
        }catch (DataAccessException ex){

            return new ResponseEntity<>(response.builder().mensaje(ex.getMessage()).objecto(null),HttpStatus.INTERNAL_SERVER_ERROR);

        }
    }


}
