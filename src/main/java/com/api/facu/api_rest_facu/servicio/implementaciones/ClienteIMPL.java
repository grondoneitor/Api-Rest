package com.api.facu.api_rest_facu.servicio.implementaciones;

import com.api.facu.api_rest_facu.Models.DAO.IClienteDao;
import com.api.facu.api_rest_facu.Models.dto.ClienteDto;
import com.api.facu.api_rest_facu.Models.entity.Cliente;
import com.api.facu.api_rest_facu.servicio.IClienteServicio;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class ClienteIMPL implements IClienteServicio {

    @Autowired
    private IClienteDao clienteDao;


    @Transactional
    @Override
    public Cliente save(ClienteDto clienteDto) {
        Cliente cliente = Cliente.builder()
                .idCliente(clienteDto.getIdCliente())
                .nombre(clienteDto.getNombre())
                .apellido(clienteDto.getApellido())
                .correo(clienteDto.getCorreo())
                .fechaRegistro(clienteDto.getFechaRegistro())
                .build();
        return clienteDao.save(cliente);

    }
    @Transactional(readOnly = true)
    @Override
    public Cliente finById(Integer id) {

        return clienteDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Cliente cliente) {
     clienteDao.delete(cliente);
    }
}
