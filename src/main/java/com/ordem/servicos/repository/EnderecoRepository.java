package com.ordem.servicos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ordem.servicos.models.Cliente;
import com.ordem.servicos.models.Endereco;

public interface EnderecoRepository extends CrudRepository<Endereco, Long>{
	
	Endereco findByCep(String cep);
	Iterable<Endereco> findByCliente(Cliente cliente);
	
}
