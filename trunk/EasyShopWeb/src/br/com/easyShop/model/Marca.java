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
	private Long pkMarca;
	private String nome;
	private Integer status;
	@OneToMany (mappedBy="marca")
	private List<Produto> produtos;
	
	public Marca() { }
	
	public Long getPkMarca() {
		return pkMarca;
	}

	public void setPkMarca(Long pkMarca) {
		this.pkMarca = pkMarca;
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

	public List<Produto> getProdutos() {
		return produtos;
	}

	public void setProdutos(List<Produto> produtos) {
		this.produtos = produtos;
	}

	public String toString(){  
	    return this.nome;  
	}
	
}
