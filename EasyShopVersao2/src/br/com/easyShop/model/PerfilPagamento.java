package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.easyShop.utils.Constantes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class PerfilPagamento
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkPerfilPagamento;
	@Column(length=500)
	private String nome;
	@Column(length=5000)
	private String descricao;
	private Integer status = Constantes.STATUS_ATIVO;

	@OneToMany(mappedBy="perfilPagamento")
	private List<Pedido> pedidos;


	/*-*-*-* Construtores *-*-*-*/
	public PerfilPagamento() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public Long getPkPerfilPagamento() { return pkPerfilPagamento; }
	public void setPkPerfilPagamento(Long pkPerfilPagamento) { this.pkPerfilPagamento = pkPerfilPagamento; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }

	public Integer getStatus() { return status; }
	public void setStatus(Integer status) { this.status = status; }

	public List<Pedido> getPedidos() { if(pedidos==null) { pedidos = new ArrayList<Pedido>(); } return pedidos; }
	public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }
}