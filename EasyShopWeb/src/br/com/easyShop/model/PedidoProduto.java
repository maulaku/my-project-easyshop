package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="pedidoproduto", schema="easy")
public class PedidoProduto
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkPedidoProduto;
	private Integer quantidade;
	@ManyToOne @JoinColumn(name="fkPedido")
	private Pedido pedido;
	@ManyToOne @JoinColumn(name="fkProduto")
	private Produto produto;
	
	public PedidoProduto() {}

	public Long getPkPedidoProduto()
	{
		return pkPedidoProduto;
	}

	public void setPkPedidoProduto(Long pkPedidoProduto)
	{
		this.pkPedidoProduto = pkPedidoProduto;
	}

	public Integer getQuantidade()
	{
		return quantidade;
	}

	public void setQuantidade(Integer quantidade)
	{
		this.quantidade = quantidade;
	}

	public Pedido getPedido()
	{
		return pedido;
	}

	public void setPedido(Pedido pedido)
	{
		this.pedido = pedido;
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