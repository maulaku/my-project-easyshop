package br.com.easyShop.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="marca", schema="easy")
public class Marca {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkMarca;
	private String nome;
	private int status;
	@OneToMany
	private List<Produto> produtos;
	
	public Marca() { }
	
	public long getPkMarca() {
		return pkMarca;
	}
	
	public void setPkMarca(long pkMarca) {
		this.pkMarca = pkMarca;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}

	public List<Produto> getProdutos()
	{
		return produtos;
	}

	public void setProdutos(List<Produto> produtos)
	{
		this.produtos = produtos;
	}
	
	public String toString(){  
	    return this.nome;  
	}
	
}
