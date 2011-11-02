package br.com.easyShop.model;

import java.util.Date;
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
@Table(name="pedido", schema="easy")
public class Pedido
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkPedido;
	private double total;
	private Date dataPedido;
	private Date dataEntrega;
    private int status;   
    @ManyToOne @JoinColumn(name="fkEndereco")
    private Endereco endereco;
    @ManyToOne @JoinColumn(name="fkPerfilPagamento")
    private PerfilPagamento perfilPagamento;
    @ManyToOne @JoinColumn(name="fkCliente")
    private Cliente cliente;
    @OneToMany
    private List<PedidoProduto> pedidoProdutos;
    
	public long getPkPedido() {
		return pkPedido;
	}
	
	public void setPkPedido(long pkPedido) {
		this.pkPedido = pkPedido;
	}
	
	public double getTotal() {
		return total;
	}
	
	public void setTotal(double total) {
		this.total = total;
	}
	
	public Date getDataPedido() {
		return dataPedido;
	}
	
	public void setDataPedido(Date dataPedido) {
		this.dataPedido = dataPedido;
	}
	
	public Date getDataEntrega() {
		return dataEntrega;
	}
	
	public void setDataEntrega(Date dataEntrega) {
		this.dataEntrega = dataEntrega;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public PerfilPagamento getPerfilPagamento() {
		return perfilPagamento;
	}
	
	public void setPerfilPagamento(PerfilPagamento perfilPagamento) {
		this.perfilPagamento = perfilPagamento;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}
	
	public Endereco getEndereco() {
		return endereco;
	}
	
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
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
