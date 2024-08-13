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
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class ClienteControler {

    @Autowired
    private IClienteServicio clienteIMPL;

    @GetMapping("clientes")
    public ResponseEntity<?> showAll(){
        List<Cliente> listaClientes = clienteIMPL.listAll();
        try{
            if(listaClientes == null) {
                return new ResponseEntity<>(response.builder().mensaje("No hay clientes disponibles").objecto(null),HttpStatus.OK);

            }else {
                return new ResponseEntity<>(response.builder().mensaje("").objecto(listaClientes).build(), HttpStatus.OK);
            }
        }catch (DataAccessException ex){
            return new ResponseEntity<>(response.builder().mensaje(ex.getMessage()).objecto(null),HttpStatus.OK);

        }
    }



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

            return new ResponseEntity<>(response.builder().mensaje(ex.getMessage()).objecto(null),HttpStatus.METHOD_NOT_ALLOWED);

        }
    }

    @PutMapping("cliente/{id}")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<?>  update(@RequestBody ClienteDto clienteDto, @PathVariable Integer id) {
        try{
            if(clienteIMPL.existsById(id)){
                clienteDto.setIdCliente(id);
                Cliente clienteUpdate = clienteIMPL.save(clienteDto);
                ClienteDto cliente =ClienteDto.builder()
                        .idCliente(clienteUpdate.getIdCliente())
                        .nombre(clienteUpdate.getNombre())
                        .apellido(clienteUpdate.getApellido())
                        .correo(clienteUpdate.getCorreo())
                        .fechaRegistro(clienteUpdate.getFechaRegistro())
                        .build();
                return new ResponseEntity<>(response.builder().mensaje("Actualizado Correctamente").objecto(cliente).build(), HttpStatus.CREATED);
            }else{
                return new ResponseEntity<>(response.builder().mensaje("No se puede actualizar un cliente inexistente").objecto(null),HttpStatus.METHOD_NOT_ALLOWED);

            }


        }catch (DataAccessException ex){

            return new ResponseEntity<>(response.builder().mensaje(ex.getMessage()).objecto(null),HttpStatus.METHOD_NOT_ALLOWED);

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
                                                , HttpStatus.METHOD_NOT_ALLOWED);

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

            return new ResponseEntity<>(response.builder().mensaje("No se encontro un cliente con ese id").objecto(null),HttpStatus.NOT_FOUND);

        }
    }


}
