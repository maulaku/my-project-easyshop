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
public class Pessoa
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkPessoa;
	@Column(length=500)
	private String foto;
	private int status = Constantes.STATUS_ATIVO;

	@ManyToOne @JoinColumn(name="fkPessoaJuridica")
	private PessoaJuridica pessoaJuridica;

	@ManyToOne @JoinColumn(name="fkPessoaFisica")
	private PessoaFisica pessoaFisica;

	@OneToMany(mappedBy="pessoa")
	private List<Usuario> usuarios;

	@OneToMany(mappedBy="pessoa")
	private List<Contato> contatos;

	@OneToMany(mappedBy="pessoa")
	private List<Cliente> clientes;

	@OneToMany(mappedBy="pessoa")
	private List<Endereco> enderecos;


	/*-*-*-* Construtores *-*-*-*/
	public Pessoa() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkPessoa() { return pkPessoa; }
	public void setPkPessoa(long pkPessoa) { this.pkPessoa = pkPessoa; }

	public String getFoto() { return foto; }
	public void setFoto(String foto) { this.foto = foto; }

	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }

	public PessoaJuridica getPessoaJuridica() { return pessoaJuridica; }
	public void setPessoaJuridica(PessoaJuridica pessoaJuridica) { this.pessoaJuridica = pessoaJuridica; }

	public PessoaFisica getPessoaFisica() { return pessoaFisica; }
	public void setPessoaFisica(PessoaFisica pessoaFisica) { this.pessoaFisica = pessoaFisica; }

	public List<Usuario> getUsuarios() { if(usuarios==null) { usuarios = new ArrayList<Usuario>(); } return usuarios; }
	public void setUsuarios(List<Usuario> usuarios) { this.usuarios = usuarios; }

	public List<Contato> getContatos() { if(contatos==null) { contatos = new ArrayList<Contato>(); } return contatos; }
	public void setContatos(List<Contato> contatos) { this.contatos = contatos; }

	public List<Cliente> getClientes() { if(clientes==null) { clientes = new ArrayList<Cliente>(); } return clientes; }
	public void setClientes(List<Cliente> clientes) { this.clientes = clientes; }

	public List<Endereco> getEnderecos() { if(enderecos==null) { enderecos = new ArrayList<Endereco>(); } return enderecos; }
	public void setEnderecos(List<Endereco> enderecos) { this.enderecos = enderecos; }
}