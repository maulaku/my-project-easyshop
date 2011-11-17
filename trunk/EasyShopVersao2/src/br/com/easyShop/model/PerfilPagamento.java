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
	private long pkPerfilPagamento;
	@Column(length=500)
	private String nome;
	@Column(length=5000)
	private String descricao;
	private int status = Constantes.STATUS_ATIVO;

	@OneToMany(mappedBy="perfilPagamento")
	private List<Pedido> pedidos;


	/*-*-*-* Construtores *-*-*-*/
	public PerfilPagamento() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkPerfilPagamento() { return pkPerfilPagamento; }
	public void setPkPerfilPagamento(long pkPerfilPagamento) { this.pkPerfilPagamento = pkPerfilPagamento; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getDescricao() { return descricao; }
	public void setDescricao(String descricao) { this.descricao = descricao; }

	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }

	public List<Pedido> getPedidos() { if(pedidos==null) { pedidos = new ArrayList<Pedido>(); } return pedidos; }
	public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }
}