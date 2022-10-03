package com.ordem.servicos.repository;

import org.springframework.data.repository.CrudRepository;

import com.ordem.servicos.models.Motoboy;

public interface MotoboyRepository extends CrudRepository<Motoboy, Long>{

	Motoboy findById(long id);
	Motoboy findByNome(String nome);
	
}
