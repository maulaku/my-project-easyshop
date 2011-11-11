package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.easyShop.utils.Constantes;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class Produto
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkProduto;
	@Column(length=500)
	private String nome;
	@Column(length=500)
	private String codigo;
	private Double preco;
	private int quantidade;
	private Boolean promocao;
	private Integer garantia;
	@Column(length=5000)
	private String descricao;
	@Column(length=5000)
	private String especificacoesTecnicas;
	@Column(length=5000)
	private String caracteristicas;
	private Integer status = Constantes.STATUS_ATIVO;

	@ManyToOne @JoinColumn(name="fkMarca")
	private Marca marca;

	@ManyToOne @JoinColumn(name="fkCategoria")
	private Categoria categoria;

	@OneToMany(mappedBy="produto")
	private List<PedidoProduto> pedidoProdutos;

	@OneToMany(mappedBy="produto")
	private List<DesejoProduto> desejoProdutos;

	@OneToMany(mappedBy="produto")
	private List<CarrinhoProduto> carrinhoProdutos;


	/*-*-*-* Construtores *-*-*-*/
	public Produto() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkProduto() { return pkProduto; }
	public void setPkProduto(long pkProduto) { this.pkProduto = pkProduto; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getCodigo() { return codigo; }
	public void setCodigo(String codigo) { this.codigo = codigo; }

	public Double getPreco() { return preco; }
	public void setPreco(Double preco) { this.preco = preco; }

	public int getQuantidade() { return quantidade; }
	public void setQuantidade(int quantidade) { this.quantidade = quantidade; }

	public Boolean getPromocao() { return promocao; }
	public void setPromocao(Boolean promocao) { this.promocao = promocao; }

	public Integer getGarantia() { return garantia; }
	public void setGarantia(Integer garantia) { this.garantia = garantia; }

	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }

	public String getEspecificacoesTecnicas() { return especificacoesTecnicas; }
	public void setEspecificacoesTecnicas(String especificacoesTecnicas) { this.especificacoesTecnicas = especificacoesTecnicas; }

	public String getCaracteristicas() { return caracteristicas; }
	public void setCaracteristicas(String caracteristicas) { this.caracteristicas = caracteristicas; }

	public Integer getStatus() { return status; }
	public void setStatus(Integer status) { this.status = status; }

	public Marca getMarca() { return marca; }
	public void setMarca(Marca marca) { this.marca = marca; }

	public Categoria getCategoria() { return categoria; }
	public void setCategoria(Categoria categoria) { this.categoria = categoria; }

	public List<PedidoProduto> getPedidoProdutos() { if(pedidoProdutos==null) { pedidoProdutos = new ArrayList<PedidoProduto>(); } return pedidoProdutos; }
	public void setPedidoProdutos(List<PedidoProduto> pedidoProdutos) { this.pedidoProdutos = pedidoProdutos; }

	public List<DesejoProduto> getDesejoProdutos() { if(desejoProdutos==null) { desejoProdutos = new ArrayList<DesejoProduto>(); } return desejoProdutos; }
	public void setDesejoProdutos(List<DesejoProduto> desejoProdutos) { this.desejoProdutos = desejoProdutos; }

	public List<CarrinhoProduto> getCarrinhoProdutos() { if(carrinhoProdutos==null) { carrinhoProdutos = new ArrayList<CarrinhoProduto>(); } return carrinhoProdutos; }
	public void setCarrinhoProdutos(List<CarrinhoProduto> carrinhoProdutos) { this.carrinhoProdutos = carrinhoProdutos; }
}