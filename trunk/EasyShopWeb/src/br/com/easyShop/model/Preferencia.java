package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.easyShop.utils.Constantes;

@Entity
@Table(name="preferencia", schema="easy")
public class Preferencia
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkPreferencia;
	private String nome;
	private Integer status = Constantes.STATUS_ATIVO;
	@ManyToOne @JoinColumn(name="fkCliente")
	private Cliente cliente;
	@ManyToOne @JoinColumn(name="fkCategoria")
	private Categoria categoria;
	
	public Preferencia() {}

	public Long getPkPreferencia() {
		return pkPreferencia;
	}

	public void setPkPreferencia(Long pkPreferencia) {
		this.pkPreferencia = pkPreferencia;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Cliente getCliente() {
		return cliente;
	}

	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public String toString(){  
	    return this.nome;  
	}
}
