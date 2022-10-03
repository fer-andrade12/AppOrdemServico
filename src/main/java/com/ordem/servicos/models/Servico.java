package com.ordem.servicos.models;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

import javax.persistence.Column;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "servico")
@SequenceGenerator(name = "seq_servico", sequenceName = "seq_servico", allocationSize = 1, initialValue = 1)
public class Servico implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_servico")
	private Long id;
	
	private String descricao;
	
	private String destino;
		
	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	@Column(nullable = false)
	private Double valor;
	
	@Column(nullable = false)
	private Double ponto;
	
	@Temporal(TemporalType.DATE)
	
	@ManyToOne(targetEntity = Cliente.class)
	@JoinColumn(name="cliente_id", nullable=false, foreignKey = @ForeignKey(value= ConstraintMode.CONSTRAINT, name="cliente_fk"))
	private Cliente cliente;
	
	@ManyToOne(targetEntity = Motoboy.class)
	@JoinColumn(name="motoboy_id", nullable=false, foreignKey = @ForeignKey(value= ConstraintMode.CONSTRAINT, name="motoboy_fk"))
	private Motoboy motoboy;
	
	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Motoboy getMotoboy() {
		return motoboy;
	}

	public void setMotoboy(Motoboy motoboy) {
		this.motoboy = motoboy;
	}
	
	private Date data;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Double getValor() {
		return valor;
	}

	public void setValor(Double valor) {
		this.valor = valor;
	}

	public Double getPonto() {
		return ponto;
	}

	public void setPonto(Double ponto) {
		this.ponto = ponto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}
	
	
}
