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
@Table(name="carrinho", schema="easy")
public class Carrinho 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkCarrinho;
	@ManyToOne @JoinColumn(name="fkCliente")
	private Cliente cliente;
	@OneToMany
	private List<CarrinhoProduto> carrinhoProdutos;
	
	public Carrinho() {}

	public long getPkCarrinho()
	{
		return pkCarrinho;
	}

	public void setPkCarrinho(long pkCarrinho)
	{
		this.pkCarrinho = pkCarrinho;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}

	public List<CarrinhoProduto> getCarrinhoProdutos()
	{
		return carrinhoProdutos;
	}

	public void setCarrinhoProdutos(List<CarrinhoProduto> carrinhoProdutos)
	{
		this.carrinhoProdutos = carrinhoProdutos;
	}
}
