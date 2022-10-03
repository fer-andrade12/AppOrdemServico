package com.ordem.servicos.models;

import java.io.Serializable;

import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;


@Entity
@Table(name = "ordem")
@SequenceGenerator(name = "seq_ordem", sequenceName = "seq_ordem", allocationSize = 1, initialValue = 1)
public class Ordem implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_ordem")
	private Long id;
	
	@ManyToOne(targetEntity = Servico.class)
	@JoinColumn(name="servico_id", nullable=false, foreignKey = @ForeignKey(value= ConstraintMode.CONSTRAINT, name="servico_fk"))
	private Servico servico;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Servico getServico() {
		return servico;
	}

	public void setServico(Servico servico) {
		this.servico = servico;
	}

}
