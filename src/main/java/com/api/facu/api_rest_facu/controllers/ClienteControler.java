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
    public Cliente create( @RequestBody ClienteDto cliente) {

        return clienteIMPL.save(cliente);
    }

    @PutMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente update(@RequestBody ClienteDto cliente) {

        return clienteIMPL.save(cliente);
    }

    @DeleteMapping("cliente/{id}")
    public ResponseEntity<?> delete(@PathVariable Integer id) {

        Map<String, Object> mapErrors = new HashMap<>();

        try{
            ClienteDto nuevoCliente = clienteIMPL.finById(id);
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
        return clienteIMPL.finById(id);
    }


}
