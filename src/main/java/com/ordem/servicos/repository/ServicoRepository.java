package com.ordem.servicos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ordem.servicos.models.Cliente;
import com.ordem.servicos.models.Servico;

public interface ServicoRepository extends CrudRepository<Servico, Long> {
	
	Servico findById(long id);
	Iterable<Servico> findByCliente(Cliente cliente);
}
