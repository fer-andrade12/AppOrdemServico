package com.ordem.servicos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ordem.servicos.models.Cliente;

public interface ClienteRepository extends CrudRepository<Cliente, Long>{

	Cliente findById(long id);
	Cliente findByNome(String nome);

}
