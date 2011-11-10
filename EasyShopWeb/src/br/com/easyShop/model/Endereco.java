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
public class Endereco
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkEndereco;
	@Column(length=500)
	private String logradouro;
	@Column(length=500)
	private String numero;
	private int tipo;
	@Column(length=500)
	private String bairro;
	@Column(length=500)
	private String cep;
	@Column(length=500)
	private String complemento;
	private int status = Constantes.STATUS_ATIVO;

	@ManyToOne @JoinColumn(name="fkPessoa")
	private Pessoa pessoa;

	@ManyToOne @JoinColumn(name="fkCidade")
	private Cidade cidade;

	@OneToMany(mappedBy="endereco")
	private List<Pedido> pedidos;


	/*-*-*-* Construtores *-*-*-*/
	public Endereco() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkEndereco() { return pkEndereco; }
	public void setPkEndereco(long pkEndereco) { this.pkEndereco = pkEndereco; }

	public String getLogradouro() { return logradouro; }
	public void setLogradouro(String logradouro) { this.logradouro = logradouro; }

	public String getNumero() { return numero; }
	public void setNumero(String numero) { this.numero = numero; }

	public int getTipo() { return tipo; }
	public void setTipo(int tipo) { this.tipo = tipo; }

	public String getBairro() { return bairro; }
	public void setBairro(String bairro) { this.bairro = bairro; }

	public String getCep() { return cep; }
	public void setCep(String cep) { this.cep = cep; }

	public String getComplemento() { return complemento; }
	public void setComplemento(String complemento) { this.complemento = complemento; }

	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }

	public Pessoa getPessoa() { return pessoa; }
	public void setPessoa(Pessoa pessoa) { this.pessoa = pessoa; }

	public Cidade getCidade() { return cidade; }
	public void setCidade(Cidade cidade) { this.cidade = cidade; }

	public List<Pedido> getPedidos() { if(pedidos==null) { pedidos = new ArrayList<Pedido>(); } return pedidos; }
	public void setPedidos(List<Pedido> pedidos) { this.pedidos = pedidos; }
}