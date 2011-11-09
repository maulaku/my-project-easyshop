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
@Table(name="contato", schema="easy")
public class Contato
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkContato;
	private Integer  tipo; 
	private String contato;
	private Integer status = Constantes.STATUS_ATIVO;
	@ManyToOne @JoinColumn(name="fkPessoa")
	private Pessoa pessoa;

	public Contato() {}

	public Long getPkContato()
	{
		return pkContato;
	}

	public void setPkContato(Long pkContato)
	{
		this.pkContato = pkContato;
	}

	public Integer getTipo()
	{
		return tipo;
	}

	public void setTipo(Integer tipo)
	{
		this.tipo = tipo;
	}

	public String getContato()
	{
		return contato;
	}

	public void setContato(String contato)
	{
		this.contato = contato;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Pessoa getPessoa()
	{
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa)
	{
		this.pessoa = pessoa;
	}	
}
