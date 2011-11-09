package br.com.easyShop.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="pais", schema="easy")
public class Pais
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkPais;
	private String nome;
	@OneToMany (mappedBy="pais")
	private List<Estado> estados;

	public Pais() {}

	public Long getPkPais()
	{
		return pkPais;
	}
	public void setPkPais(Long pkPais)
	{
		this.pkPais = pkPais;
	}
	public String getNome()
	{
		return nome;
	}
	public void setNome(String nome)
	{
		this.nome = nome;
	}
	public List<Estado> getEstados()
	{
		return estados;
	}
	public void setEstados(List<Estado> estados)
	{
		this.estados = estados;
	}
	public String toString(){
	        return this.nome;
	 }
}
