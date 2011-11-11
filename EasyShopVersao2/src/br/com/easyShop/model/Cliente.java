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
public class Cliente
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkCliente;
	@Column(length=500)
	private String codigo;
	private int status = Constantes.STATUS_ATIVO;

	@ManyToOne @JoinColumn(name="fkPessoa")
	private Pessoa pessoa;

	@OneToMany(mappedBy="cliente")
	private List<Preferencia> preferencias;

	@OneToMany(mappedBy="cliente")
	private List<Desejo> desejos;

	@OneToMany(mappedBy="cliente")
	private List<Carrinho> carrinhos;

	@OneToMany(mappedBy="cliente")
	private List<Pedido> pedidos;


	/*-*-*-* Construtores *-*-*-*/
	public Cliente() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkCliente() { return pkCliente; }
	public void setPkCliente(long pkCliente) { this.pkCliente = pkCliente; }

	public String getCodigo() { return codigo; }
	public void setCodigo(String codigo) { this.codigo = codigo; }

	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }

	public Pessoa getPessoa() { return pessoa; }
	public void setPessoa(Pessoa pessoa) { this.pessoa = pessoa; }

	public List<Preferencia> getPreferencias() { if(preferencias==null) { preferencias = new ArrayList<Preferencia>(); } return preferencias; }
	public void setPreferencias(List<Preferencia> preferencias) { this.preferencias = preferencias; }

	public List<Desejo> getDesejos() { if(desejos==null) { desejos = new ArrayList<Desejo>(); } return desejos; }
	public void setDesejos(List<Desejo> desejos) { this.desejos = desejos; }

	public List<Carrinho> getCarrinhos() { if(carrinhos==null) { carrinhos = new ArrayList<Carrinho>(); } return carrinhos; }
	public void setCarrinhos(List<Carrinho> carrinhos) { this.carrinhos = carrinhos; }

	public List<Pedido> getPedidos() { if(pedidos==null) { pedidos = new ArrayList<Pedido>(); } return pedidos; }
	public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }
}