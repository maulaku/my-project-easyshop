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
@Table(name="pessoa", schema="easy")
public class Pessoa {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkPessoa;
	private Integer status = Constantes.STATUS_ATIVO;
	@ManyToOne @JoinColumn(name="fkPessoaFisica") 
	private PessoaFisica pessoaFisica;
	@ManyToOne @JoinColumn(name="fkPessoaJuridica") 
	private PessoaJuridica pessoaJuridica;	
	@OneToMany (mappedBy="pessoa")
	private List<Usuario> usuarios;
	@OneToMany (mappedBy="pessoa")
	private List<Endereco> enderecos;
	@OneToMany (mappedBy="pessoa")
	private List<Cliente> clientes;
	@OneToMany (mappedBy="pessoa")
	private List<Contato> contatos;
	
	public Pessoa() {}

	public Long getPkPessoa()
	{
		return pkPessoa;
	}

	public void setPkPessoa(Long pkPessoa)
	{
		this.pkPessoa = pkPessoa;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public PessoaFisica getPessoaFisica()
	{
		return pessoaFisica;
	}

	public void setPessoaFisica(PessoaFisica pessoaFisica)
	{
		this.pessoaFisica = pessoaFisica;
	}

	public PessoaJuridica getPessoaJuridica()
	{
		return pessoaJuridica;
	}

	public void setPessoaJuridica(PessoaJuridica pessoaJuridica)
	{
		this.pessoaJuridica = pessoaJuridica;
	}

	public List<Usuario> getUsuarios()
	{
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios)
	{
		this.usuarios = usuarios;
	}

	public List<Endereco> getEnderecos()
	{
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos)
	{
		this.enderecos = enderecos;
	}

	public List<Cliente> getClientes()
	{
		return clientes;
	}

	public void setClientes(List<Cliente> clientes)
	{
		this.clientes = clientes;
	}

	public List<Contato> getContatos()
	{
		return contatos;
	}

	public void setContatos(List<Contato> contatos)
	{
		this.contatos = contatos;
	}
}
