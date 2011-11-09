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

@Entity
@Table(name="produto", schema="easy")
public class Produto {

	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkProduto;
	private String nome;
	private String codigo;
	private Double preco;
	private Integer quantidade;
	private Integer garantia;
	private String descricao;
	private Integer status=Constantes.STATUS_ATIVO;
    @ManyToOne @JoinColumn(name="fkMarca")
	private Marca marca;
    @ManyToOne @JoinColumn(name="fkCategoria")
	private Categoria categoria;
	@OneToMany (mappedBy="produto")
	private List<DesejoProduto> desejoProdutos;
	@OneToMany (mappedBy="produto")
	private List<PedidoProduto> pedidoProdutos;
	@OneToMany (mappedBy="produto")
	private List<CarrinhoProduto> carrinhoProdutos;
    
	public Produto() { }
	
	public Long getPkProduto() {
		return pkProduto;
	}

	public void setPkProduto(Long pkProduto) {
		this.pkProduto = pkProduto;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Double getPreco() {
		return preco;
	}

	public void setPreco(Double preco) {
		this.preco = preco;
	}

	public Integer getQuantidade() {
		return quantidade;
	}

	public void setQuantidade(Integer quantidade) {
		this.quantidade = quantidade;
	}

	public Integer getGarantia() {
		return garantia;
	}

	public void setGarantia(Integer garantia) {
		this.garantia = garantia;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
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

	public List<DesejoProduto> getDesejoProdutos() {
		return desejoProdutos;
	}

	public void setDesejoProdutos(List<DesejoProduto> desejoProdutos) {
		this.desejoProdutos = desejoProdutos;
	}

	public List<PedidoProduto> getPedidoProdutos() {
		return pedidoProdutos;
	}

	public void setPedidoProdutos(List<PedidoProduto> pedidoProdutos) {
		this.pedidoProdutos = pedidoProdutos;
	}

	public List<CarrinhoProduto> getCarrinhoProdutos() {
		return carrinhoProdutos;
	}

	public void setCarrinhoProdutos(List<CarrinhoProduto> carrinhoProdutos) {
		this.carrinhoProdutos = carrinhoProdutos;
	}

	public String toString(){  
	    return this.nome;  
	}
	
}
