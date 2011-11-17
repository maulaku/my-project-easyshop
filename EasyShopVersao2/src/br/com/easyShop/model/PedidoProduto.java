package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class PedidoProduto
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkPedidoProduto;
	private int quantidade;

	@ManyToOne @JoinColumn(name="fkPedido")
	private Pedido pedido;

	@ManyToOne @JoinColumn(name="fkProduto")
	private Produto produto;


	/*-*-*-* Construtores *-*-*-*/
	public PedidoProduto() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkPedidoProduto() { return pkPedidoProduto; }
	public void setPkPedidoProduto(long pkPedidoProduto) { this.pkPedidoProduto = pkPedidoProduto; }

	public int getQuantidade() { return quantidade; }
	public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

	public Pedido getPedido() { return pedido; }
	public void setPedido(Pedido pedido) { this.pedido = pedido; }

	public Produto getProduto() { return produto; }
	public void setProduto(Produto produto) { this.produto = produto; }
}