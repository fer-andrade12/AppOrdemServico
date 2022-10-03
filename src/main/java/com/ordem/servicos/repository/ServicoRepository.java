package com.ordem.servicos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ordem.servicos.models.Servico;

public interface ServicoRepository extends CrudRepository<Servico, Long> {
	
	Servico findById(long id);
}
