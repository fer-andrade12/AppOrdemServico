package com.ordem.servicos.models;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.ConstraintMode;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import com.ordem.servicos.enuns.TipoEndereco;

@Entity
@Table(name= "endereco")
@SequenceGenerator(name="seq_endereco", sequenceName ="seq_endereco", allocationSize=1, initialValue = 1)
public class Endereco implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_endereco")
	private Long id;
	
	@Column(nullable = false)
	private String cep;
	
	@Column(nullable = false)
	private String logradouro;
	
	@Column(nullable = false)
	private String numero;
	
	private String complemento;
	
	@Column(nullable = false)
	private String bairro;
	
	@Column(nullable = false)
	private String uf;
	
	@Column(nullable = false)
	private String localidade;
	
	@ManyToOne(targetEntity = Cliente.class)
	@JoinColumn(name="cliente_id", nullable=false, foreignKey = @ForeignKey(value= ConstraintMode.CONSTRAINT, name="cliente_fk"))
	private Cliente cliente;

	public Long getIdEndereco() {
		return id;
	}

	public void setIdEndereco(Long idEndereco) {
		id = idEndereco;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getLocalidade() {
		return localidade;
	}

	public void setLocalidade(String localidade) {
		this.localidade = localidade;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}


	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
}