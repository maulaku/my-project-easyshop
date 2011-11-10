package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class CarrinhoProduto
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkCarrinhoProduto;
	private Integer quantidade;

	@ManyToOne @JoinColumn(name="fkCarrinho")
	private Carrinho carrinho;

	@ManyToOne @JoinColumn(name="fkProduto")
	private Produto produto;


	/*-*-*-* Construtores *-*-*-*/
	public CarrinhoProduto() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkCarrinhoProduto() { return pkCarrinhoProduto; }
	public void setPkCarrinhoProduto(long pkCarrinhoProduto) { this.pkCarrinhoProduto = pkCarrinhoProduto; }

	public Integer getQuantidade() { return quantidade; }
	public void setQuantidade(Integer quantidade) { this.quantidade = quantidade; }

	public Carrinho getCarrinho() { return carrinho; }
	public void setCarrinho(Carrinho carrinho) { this.carrinho = carrinho; }

	public Produto getProduto() { return produto; }
	public void setProduto(Produto produto) { this.produto = produto; }
}