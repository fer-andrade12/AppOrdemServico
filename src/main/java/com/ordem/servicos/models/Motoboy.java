package com.ordem.servicos.models;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "motoboy")
@SequenceGenerator(name = "seq_motoboy", sequenceName = "seq_motoboy", allocationSize = 1, initialValue = 1)
public class Motoboy implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_motoboy")
	private Long id;
	
	private String nome;
	
	private String telefone;
	
	private String email;
	
	public List<Servico> getServico() {
		return servicos;
	}

	public void setServico(List<Servico> servico) {
		this.servicos = servico;
	}

	@OneToMany(mappedBy = "motoboy", cascade = CascadeType.REMOVE)
	private List<Servico> servicos;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getTelefone() {
		return telefone;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Motoboy other = (Motoboy) obj;
		return Objects.equals(id, other.id);
	}

}
