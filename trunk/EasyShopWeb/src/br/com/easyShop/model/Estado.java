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
@Table(name="estado", schema="easy")
public class Estado
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkEstado;
	private String nome;
	private String sigla;	  
	@ManyToOne @JoinColumn(name="fkPais")
	private Pais pais;
	@OneToMany (mappedBy="estado")
	private List<Cidade> cidades;
	
	public Estado(){}
	
	public Long getPkEstado()
	{
		return pkEstado;
	}

	public void setPkEstado(Long pkEstado)
	{
		this.pkEstado = pkEstado;
	}

	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public List<Cidade> getCidades()
	{
		return cidades;
	}
	public void setCidades(List<Cidade> cidades)
	{
		this.cidades = cidades;
	}
	public String getSigla()
	{
		return sigla;
	}
	public void setSigla(String sigla)
	{
		this.sigla = sigla;
	}
	public Pais getPais()
	{
		return pais;
	}
	public void setPais(Pais pais)
	{
		this.pais = pais;
	}
	
	public String toString(){  
	    return this.nome;  
	} 
}
