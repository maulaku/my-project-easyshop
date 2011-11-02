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
@Table(name="produto", schema="easy")
public class Produto {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkProduto;
	private String nome;
	private int codigo;
	private double preco;
	private int quantidade;
	private int garantia;
	private String descricao;
	private int status;
    @ManyToOne @JoinColumn(name="fkMarca")
	private Marca marca;
    @ManyToOne @JoinColumn(name="fkCategoria")
	private Categoria categoria;
	@OneToMany
	private List<DesejoProduto> desejoProdutos;
	@OneToMany
	private List<PedidoProduto> pedidoProdutos;
	@OneToMany
	private List<CarrinhoProduto> carrinhoProdutos;
    
	public Produto() { }
		
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public int getQuantidade() {
		return quantidade;
	}
	
	public void setQuantidade(int quantidade) {
		this.quantidade = quantidade;
	}
	
	public int getGarantia() {
		return garantia;
	}
	
	public void setGarantia(int garantia) {
		this.garantia = garantia;
	}
	
	public String getDescricao() {
		return descricao;
	}
	
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
	public int getStatus() {
		return status;
	}
	
	public void setStatus(int status) {
		this.status = status;
	}
	
	public Marca getMarca() {
		return marca;
	}
	
	public void setMarca(Marca marca) {
		this.marca = marca;
	}
	
	public Categoria getCategoria() {
		return categoria;
	}
	
	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public long getPkProduto()
	{
		return pkProduto;
	}

	public void setPkProduto(long pkProduto)
	{
		this.pkProduto = pkProduto;
	}

	public List<DesejoProduto> getDesejoProdutos()
	{
		return desejoProdutos;
	}

	public void setDesejoProdutos(List<DesejoProduto> desejoProdutos)
	{
		this.desejoProdutos = desejoProdutos;
	}

	public List<PedidoProduto> getPedidoProdutos()
	{
		return pedidoProdutos;
	}

	public void setPedidoProdutos(List<PedidoProduto> pedidoProdutos)
	{
		this.pedidoProdutos = pedidoProdutos;
	}

	public List<CarrinhoProduto> getCarrinhoProdutos()
	{
		return carrinhoProdutos;
	}

	public void setCarrinhoProdutos(List<CarrinhoProduto> carrinhoProdutos)
	{
		this.carrinhoProdutos = carrinhoProdutos;
	}

	public double getPreco() {
		return preco;
	}

	public void setPreco(double preco) {
		this.preco = preco;
	}
}
