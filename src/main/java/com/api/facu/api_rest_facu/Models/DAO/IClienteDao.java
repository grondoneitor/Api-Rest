package com.api.facu.api_rest_facu.Models.DAO;

import com.api.facu.api_rest_facu.Models.entity.Cliente;
import org.springframework.data.repository.CrudRepository;

public interface IClienteDao extends CrudRepository<Cliente, Integer> {
}
