package br.com.easyShop.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.easyShop.utils.Constantes;

@Entity
@Table(name="perfilpagamento", schema="easy")
public class PerfilPagamento
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkPerfilPagamento;
	private String nome;
	private String descricao;
	private Integer status = Constantes.STATUS_ATIVO;
	@OneToMany (mappedBy="perfilpagamento")
	private List<Pedido> pedidos;
	
	public PerfilPagamento() {}

	public Long getPkPerfilPagamento()
	{
		return pkPerfilPagamento;
	}

	public void setPkPerfilPagamento(Long pkPerfilPagamento)
	{
		this.pkPerfilPagamento = pkPerfilPagamento;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public String getDescricao()
	{
		return descricao;
	}

	public void setDescricao(String descricao)
	{
		this.descricao = descricao;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public List<Pedido> getPedidos()
	{
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos)
	{
		this.pedidos = pedidos;
	}	
}
