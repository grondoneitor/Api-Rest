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

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ClienteControler {

    @Autowired
    private IClienteServicio clienteIMPL;

    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto create( @RequestBody ClienteDto clienteDto) {
        Cliente clienteSave = clienteIMPL.save(clienteDto);
        return ClienteDto.builder()
                .idCliente(clienteSave.getIdCliente())
                .nombre(clienteSave.getNombre())
                .apellido(clienteSave.getApellido())
                .correo(clienteSave.getCorreo())
                .fechaRegistro(clienteSave.getFechaRegistro())
                .build();
    }

    @PutMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public ClienteDto update(@RequestBody ClienteDto clienteDto) {
        Cliente clienteUpdate = clienteIMPL.save(clienteDto);
        return ClienteDto.builder()
                .idCliente(clienteUpdate.getIdCliente())
                .nombre(clienteUpdate.getNombre())
                .apellido(clienteUpdate.getApellido())
                .correo(clienteUpdate.getCorreo())
                .fechaRegistro(clienteUpdate.getFechaRegistro())
                .build();
    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        Map<String, Object> mapErrors = new HashMap<>();

        try{
            Cliente nuevoCliente = clienteIMPL.finById(id);
            clienteIMPL.delete(nuevoCliente);

            return new ResponseEntity<>(nuevoCliente, HttpStatus.NO_CONTENT);

        }catch (DataAccessException ex){

             mapErrors.put("mensaje", ex.getMessage());
             mapErrors.put("cliente",null);
            return new ResponseEntity<>(mapErrors, HttpStatus.INTERNAL_SERVER_ERROR);

        }

    }

    @GetMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClienteDto showById(@PathVariable Integer id){
        Cliente clienteById = clienteIMPL.finById(id);
        return ClienteDto.builder()
                .idCliente(clienteById.getIdCliente())
                .nombre(clienteById.getNombre())
                .apellido(clienteById.getApellido())
                .correo(clienteById.getCorreo())
                .fechaRegistro(clienteById.getFechaRegistro())
                .build();
    }


}
