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
	private int pkPessoa;
	private int status = Constantes.STATUS_ATIVO;
	@ManyToOne @JoinColumn(name="fkPessoaFisica") 
	private PessoaFisica pessoaFisica;
	@ManyToOne @JoinColumn(name="fkPessoaJuridica") 
	private PessoaJuridica pessoaJuridica;	
	@OneToMany
	private List<Usuario> usuarios;
	@OneToMany
	private List<Endereco> enderecos;
	@OneToMany
	private List<Cliente> clientes;
	@OneToMany
	private List<Contato> contatos;
	
	public Pessoa() {}

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

	public List<Endereco> getEnderecos()
	{
		return enderecos;
	}

	public void setEnderecos(List<Endereco> enderecos)
	{
		this.enderecos = enderecos;
	}
	
	public int getPkPessoa()
	{
		return pkPessoa;
	}
	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public void setPkPessoa(int pkPessoa)
	{
		this.pkPessoa = pkPessoa;
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


}
