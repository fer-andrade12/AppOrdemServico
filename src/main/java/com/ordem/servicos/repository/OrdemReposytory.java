package com.ordem.servicos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ordem.servicos.models.Ordem;

public interface OrdemReposytory extends CrudRepository<Ordem, Long>{
	Ordem findById(long id);
}
