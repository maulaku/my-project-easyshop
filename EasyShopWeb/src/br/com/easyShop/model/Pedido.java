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

import br.com.easyShop.utils.Constantes;

import utils.data.Data;

@Entity
@Table(name="pedido", schema="easy")
public class Pedido
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkPedido;
	private Double total;
	private Data dataPedido;
	private Data dataEntrega;
    private Integer status = Constantes.STATUS_ATIVO;   
    @ManyToOne @JoinColumn(name="fkEndereco")
    private Endereco endereco;
    @ManyToOne @JoinColumn(name="fkPerfilPagamento")
    private PerfilPagamento perfilPagamento;
    @ManyToOne @JoinColumn(name="fkCliente")
    private Cliente cliente;
    @OneToMany (mappedBy="pedido")
    private List<PedidoProduto> pedidoProdutos;
    
	public Pedido() {}

	public Long getPkPedido()
	{
		return pkPedido;
	}

	public void setPkPedido(Long pkPedido)
	{
		this.pkPedido = pkPedido;
	}

	public Double getTotal()
	{
		return total;
	}

	public void setTotal(Double total)
	{
		this.total = total;
	}

	public Data getDataPedido()
	{
		return dataPedido;
	}

	public void setDataPedido(Data dataPedido)
	{
		this.dataPedido = dataPedido;
	}

	public Data getDataEntrega()
	{
		return dataEntrega;
	}

	public void setDataEntrega(Data dataEntrega)
	{
		this.dataEntrega = dataEntrega;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Endereco getEndereco()
	{
		return endereco;
	}

	public void setEndereco(Endereco endereco)
	{
		this.endereco = endereco;
	}

	public PerfilPagamento getPerfilPagamento()
	{
		return perfilPagamento;
	}

	public void setPerfilPagamento(PerfilPagamento perfilPagamento)
	{
		this.perfilPagamento = perfilPagamento;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}

	public List<PedidoProduto> getPedidoProdutos()
	{
		return pedidoProdutos;
	}

	public void setPedidoProdutos(List<PedidoProduto> pedidoProdutos)
	{
		this.pedidoProdutos = pedidoProdutos;
	}
}
