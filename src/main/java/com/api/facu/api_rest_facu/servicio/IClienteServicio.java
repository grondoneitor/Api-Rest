package com.api.facu.api_rest_facu.servicio;

import com.api.facu.api_rest_facu.Models.dto.ClienteDto;
import com.api.facu.api_rest_facu.Models.entity.Cliente;

import java.util.List;

public interface IClienteServicio  {


    Cliente save(ClienteDto cliente);

    Cliente finById(Integer id);

    void delete(Cliente cliente);

    boolean existsById(Integer id);

    List<Cliente> listAll();
}
