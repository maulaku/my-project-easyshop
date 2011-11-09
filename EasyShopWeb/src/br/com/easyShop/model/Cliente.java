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
	private Long pkCliente;
	private String codigo;
	private Integer status = Constantes.STATUS_ATIVO;
	@ManyToOne @JoinColumn(name="fkPessoa")
	private Pessoa pessoa;
	@OneToMany (mappedBy="cliente")
	private List<Pedido> pedidos;
	@OneToMany (mappedBy="cliente")
	private List<Carrinho> carrinhos;
	@OneToMany (mappedBy="cliente")
	private List<Preferencia> preferencias;
	@OneToMany (mappedBy="cliente")
	private List<Desejo> desejos;
	
	public Cliente() {}

	public Long getPkCliente()
	{
		return pkCliente;
	}

	public void setPkCliente(Long pkCliente)
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

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
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