package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class Carrinho
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkCarrinho;

	@ManyToOne @JoinColumn(name="fkCliente")
	private Cliente cliente;

	@OneToMany(mappedBy="carrinho")
	private List<CarrinhoProduto> carrinhoProdutos;


	/*-*-*-* Construtores *-*-*-*/
	public Carrinho() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkCarrinho() { return pkCarrinho; }
	public void setPkCarrinho(long pkCarrinho) { this.pkCarrinho = pkCarrinho; }

	public Cliente getCliente() { return cliente; }
	public void setCliente(Cliente cliente) { this.cliente = cliente; }

	public List<CarrinhoProduto> getCarrinhoProdutos() { if(carrinhoProdutos==null) { carrinhoProdutos = new ArrayList<CarrinhoProduto>(); } return carrinhoProdutos; }
	public void setCarrinhoProdutos(List<CarrinhoProduto> carrinhoProdutos) { this.carrinhoProdutos = carrinhoProdutos; }
}