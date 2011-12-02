package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class Categoria
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkCategoria;
	@Column(length=500)
	private String nome;
	@Column(length=500)
	private String imagem;
	private Integer tipo;

	@ManyToOne @JoinColumn(name="fkCategoria")
	private Categoria subCategoria;

	@OneToMany(mappedBy="subCategoria")
	private List<Categoria> categorias;

	@OneToMany(mappedBy="categoria")
	private List<Preferencia> preferencias;

	@OneToMany(mappedBy="categoria")
	private List<Produto> produtos;


	/*-*-*-* Construtores *-*-*-*/
	public Categoria() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkCategoria() { return pkCategoria; }
	public void setPkCategoria(long pkCategoria) { this.pkCategoria = pkCategoria; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getImagem() { return imagem; }
	public void setImagem(String imagem) { this.imagem = imagem; }

	public Integer getTipo() { return tipo; }
	public void setTipo(Integer tipo) { this.tipo = tipo; }

	public Categoria getSubCategoria() { return subCategoria; }
	public void setSubCategoria(Categoria subCategoria) { this.subCategoria = subCategoria; }

	public List<Categoria> getCategorias() { if(categorias==null) { categorias = new ArrayList<Categoria>(); } return categorias; }
	public void setCategorias(List<Categoria> categorias) { this.categorias = categorias; }

	public List<Preferencia> getPreferencias() { if(preferencias==null) { preferencias = new ArrayList<Preferencia>(); } return preferencias; }
	public void setPreferencias(List<Preferencia> preferencias) { this.preferencias = preferencias; }

	public List<Produto> getProdutos() { if(produtos==null) { produtos = new ArrayList<Produto>(); } return produtos; }
	public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }
}