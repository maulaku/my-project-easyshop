package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import org.hibernate.annotations.Type;
import utils.data.Data;
import br.com.easyShop.utils.Constantes;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class Pedido
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkPedido;
	private Double total;
	@Type(type="timestamp")
	private Data dataPedido;
	@Type(type="timestamp")
	private Data dataEntrega;
	private Integer status = Constantes.STATUS_ATIVO;

	@ManyToOne @JoinColumn(name="fkPerfilPagamento")
	private PerfilPagamento perfilPagamento;

	@ManyToOne @JoinColumn(name="fkCliente")
	private Cliente cliente;

	@ManyToOne @JoinColumn(name="fkEndereco")
	private Endereco endereco;

	@OneToMany(mappedBy="pedido")
	private List<PedidoProduto> pedidoProdutos;


	/*-*-*-* Construtores *-*-*-*/
	public Pedido() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public Long getPkPedido() { return pkPedido; }
	public void setPkPedido(Long pkPedido) { this.pkPedido = pkPedido; }

	public Double getTotal() { return total; }
	public void setTotal(Double total) { this.total = total; }

	public Data getDataPedido() { return dataPedido; }
	public void setDataPedido(Data dataPedido) { this.dataPedido = dataPedido; }

	public Data getDataEntrega() { return dataEntrega; }
	public void setDataEntrega(Data dataEntrega) { this.dataEntrega = dataEntrega; }

	public Integer getStatus() { return status; }
	public void setStatus(Integer status) { this.status = status; }

	public PerfilPagamento getPerfilPagamento() { return perfilPagamento; }
	public void setPerfilPagamento(PerfilPagamento perfilPagamento) { this.perfilPagamento = perfilPagamento; }

	public Cliente getCliente() { return cliente; }
	public void setCliente(Cliente cliente) { this.cliente = cliente; }

	public Endereco getEndereco() { return endereco; }
	public void setEndereco(Endereco endereco) { this.endereco = endereco; }

	public List<PedidoProduto> getPedidoProdutos() { if(pedidoProdutos==null) { pedidoProdutos = new ArrayList<PedidoProduto>(); } return pedidoProdutos; }
	public void setPedidoProdutos(List<PedidoProduto> pedidoProdutos) { this.pedidoProdutos = pedidoProdutos; }
}