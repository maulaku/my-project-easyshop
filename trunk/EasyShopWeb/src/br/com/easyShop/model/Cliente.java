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
@Table(name="cliente", schema="easy")
public class Cliente 
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkCliente;
	private String codigo;
	private int status = Constantes.STATUS_ATIVO;
	@ManyToOne @JoinColumn(name="fkPessoa")
	private Pessoa pessoa;
	@OneToMany
	private List<Pedido> pedidos;
	@OneToMany
	private List<Carrinho> carrinhos;
	@OneToMany
	private List<Preferencia> preferencias;
	@OneToMany
	private List<Desejo> desejos;
	
	public Cliente() {}

	public long getPkCliente()
	{
		return pkCliente;
	}

	public void setPkCliente(long pkCliente)
	{
		this.pkCliente = pkCliente;
	}

	public String getCodigo()
	{
		return codigo;
	}

	public void setCodigo(String codigo)
	{
		this.codigo = codigo;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public Pessoa getPessoa()
	{
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa)
	{
		this.pessoa = pessoa;
	}

	public List<Pedido> getPedidos()
	{
		return pedidos;
	}

	public void setPedidos(List<Pedido> pedidos)
	{
		this.pedidos = pedidos;
	}

	public List<Carrinho> getCarrinhos()
	{
		return carrinhos;
	}

	public void setCarrinhos(List<Carrinho> carrinhos)
	{
		this.carrinhos = carrinhos;
	}

	public List<Preferencia> getPreferencias()
	{
		return preferencias;
	}

	public void setPreferencias(List<Preferencia> preferencias)
	{
		this.preferencias = preferencias;
	}

	public List<Desejo> getDesejos()
	{
		return desejos;
	}

	public void setDesejos(List<Desejo> desejos)
	{
		this.desejos = desejos;
	}
}
