package com.api.facu.api_rest_facu.servicio.implementaciones;

import com.api.facu.api_rest_facu.Models.DAO.IClienteDao;
import com.api.facu.api_rest_facu.Models.entity.Cliente;
import com.api.facu.api_rest_facu.servicio.IClienteServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service

public class ClienteIMPL implements IClienteServicio {

    @Autowired
    private IClienteDao clienteDao;

    @Transactional
    @Override
    public Cliente save(Cliente cliente) {

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
