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
@Table(name="categoria", schema="easy")
public class Categoria {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkCategoria;
	private String nome;
	private Integer tipo;
	@ManyToOne @JoinColumn(name="fkCategoria")
    private Categoria subCategoria;
	@OneToMany (mappedBy="categoria")
	private List<Preferencia> preferencias;
	@OneToMany(mappedBy="categoria")
	private List<Produto> produtos;

	public Categoria() { }

	public long getPkCategoria() {
		return pkCategoria;
	}
	
	public void setPkCategoria(long pkCategoria) {
		this.pkCategoria = pkCategoria;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getTipo() {
		return tipo;
	}
	
	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Categoria getSubCategoria()
	{
		return subCategoria;
	}

	public void setSubCategoria(Categoria subCategoria)
	{
		this.subCategoria = subCategoria;
	}

	public List<Preferencia> getPreferencias()
	{
		return preferencias;
	}

	public void setPreferencias(List<Preferencia> preferencias)
	{
		this.preferencias = preferencias;
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
