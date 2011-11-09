package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="carrinhoproduto", schema="easy")
public class CarrinhoProduto
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkCarrinhoProduto;
	private Integer quantidade;
	@ManyToOne @JoinColumn(name="fkCarrinho")
	private Carrinho carrinho;
	@ManyToOne @JoinColumn(name="fkProduto")
	private Produto produto;
	
	public CarrinhoProduto() {}

	public Long getPkCarrinhoProduto()
	{
		return pkCarrinhoProduto;
	}

	public void setPkCarrinhoProduto(Long pkCarrinhoProduto)
	{
		this.pkCarrinhoProduto = pkCarrinhoProduto;
	}

	public Integer getQuantidade()
	{
		return quantidade;
	}

	public void setQuantidade(Integer quantidade)
	{
		this.quantidade = quantidade;
	}

	public Carrinho getCarrinho()
	{
		return carrinho;
	}

	public void setCarrinho(Carrinho carrinho)
	{
		this.carrinho = carrinho;
	}

	public Produto getProduto()
	{
		return produto;
	}

	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}
}
