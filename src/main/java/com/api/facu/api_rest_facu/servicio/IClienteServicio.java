package com.api.facu.api_rest_facu.servicio;

import com.api.facu.api_rest_facu.Models.entity.Cliente;

public interface IClienteServicio  {


    Cliente save(Cliente cliente);

    Cliente finById(Integer id);

    void delete(Cliente cliente);
}
