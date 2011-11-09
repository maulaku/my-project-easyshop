package br.com.easyShop.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="cidade", schema="easy")
public class Cidade
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkCidade;
	private String nome;
	@ManyToOne @JoinColumn(name="fkEstado")
	private Estado estado;
	@OneToMany (mappedBy="cidade")
	private List<Endereco> enderecos;
	
	public Cidade() {}
	
	public Long getPkCidade()
	{
		return pkCidade;
	}


	public void setPkCidade(Long pkCidade)
	{
		this.pkCidade = pkCidade;
	}


	public String getNome()
	{
		return nome;
	}


	public void setNome(String nome)
	{
		this.nome = nome;
	}


	public Estado getEstado()
	{
		return estado;
	}


	public void setEstado(Estado estado)
	{
		this.estado = estado;
	}


	public List<Endereco> getEnderecos()
	{
		return enderecos;
	}


	public void setEnderecos(List<Endereco> enderecos)
	{
		this.enderecos = enderecos;
	}


	public String toString(){  
	        return this.nome;  
	} 
}
