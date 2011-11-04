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
@Table(name="endereco", schema="easy")
public class Endereco
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkEndereco;
	private String logradouro;
	private String numero;
	private int tipo;
	private String bairro;
	private String cep;
	private String complemento;
	private int status=Constantes.STATUS_ATIVO;
	@ManyToOne @JoinColumn(name="fkPessoa")
	private Pessoa pessoa;
	@ManyToOne @JoinColumn(name="fkCidade")
	private Cidade cidade;
	@OneToMany//(fetch=FetchType.LAZY)//(fetch = FetchType.LAZY )
	private List<Pedido> pedidos;	
	
	public Endereco() {}
	
	public List<Pedido> getPedidos()
	{
		return pedidos;
	}
	public void setPedidos(List<Pedido> pedidos)
	{
		this.pedidos = pedidos;
	}
	public long getPkEndereco()
	{
		return pkEndereco;
	}
	public void setPkEndereco(long pkEndereco)
	{
		this.pkEndereco = pkEndereco;
	}
	public String getLogradouro()
	{
		return logradouro;
	}
	public void setLogradouro(String logradouro)
	{
		this.logradouro = logradouro;
	}
	public int getTipo()
	{
		return tipo;
	}
	public void setTipo(int tipo)
	{
		this.tipo = tipo;
	}
	public String getBairro()
	{
		return bairro;
	}
	public void setBairro(String bairro)
	{
		this.bairro = bairro;
	}
	public String getCep()
	{
		return cep;
	}
	public void setCep(String cep)
	{
		this.cep = cep;
	}
	public String getComplemento()
	{
		return complemento;
	}
	public void setComplemento(String complemento)
	{
		this.complemento = complemento;
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
	public Cidade getCidade()
	{
		return cidade;
	}
	public void setCidade(Cidade cidade)
	{
		this.cidade = cidade;
	}

	public String getNumero()
	{
		return numero;
	}

	public void setNumero(String numero)
	{
		this.numero = numero;
	}
	
}
