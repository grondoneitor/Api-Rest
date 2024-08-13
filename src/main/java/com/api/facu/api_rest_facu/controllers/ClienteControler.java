package com.api.facu.api_rest_facu.controllers;

import com.api.facu.api_rest_facu.Models.entity.Cliente;
import com.api.facu.api_rest_facu.servicio.IClienteServicio;
import com.api.facu.api_rest_facu.servicio.implementaciones.ClienteIMPL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ClienteControler {

    @Autowired
    private IClienteServicio clienteIMPL;

    @PostMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente create( @RequestBody  Cliente cliente) {

        return clienteIMPL.save(cliente);
    }

    @PutMapping("cliente")
    @ResponseStatus(HttpStatus.CREATED)
    public Cliente update(@RequestBody Cliente cliente) {

        return clienteIMPL.save(cliente);
    }

    @DeleteMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Integer id) {
        Cliente nuevoCliente = clienteIMPL.finById(id);
        clienteIMPL.delete(nuevoCliente);
    }

    @GetMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Cliente showById(@PathVariable Integer id){
        return clienteIMPL.finById(id);
    }


}
