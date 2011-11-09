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
@Table(name="pessoajuridica", schema="easy")
public class PessoaJuridica {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkPessoaJuridica;
	private String razaoSocial;
	private String nomeFantasia;
	private String cnpj;
	private String inscricaoEstadual;
	private Integer status = Constantes.STATUS_ATIVO;
	@OneToMany (mappedBy="pessoajuridica")
	private List<Pessoa> pessoas;
	
	public PessoaJuridica() {}

	public Long getPkPessoaJuridica()
	{
		return pkPessoaJuridica;
	}

	public void setPkPessoaJuridica(Long pkPessoaJuridica)
	{
		this.pkPessoaJuridica = pkPessoaJuridica;
	}

	public String getRazaoSocial()
	{
		return razaoSocial;
	}

	public void setRazaoSocial(String razaoSocial)
	{
		this.razaoSocial = razaoSocial;
	}

	public String getNomeFantasia()
	{
		return nomeFantasia;
	}

	public void setNomeFantasia(String nomeFantasia)
	{
		this.nomeFantasia = nomeFantasia;
	}

	public String getCnpj()
	{
		return cnpj;
	}

	public void setCnpj(String cnpj)
	{
		this.cnpj = cnpj;
	}

	public String getInscricaoEstadual()
	{
		return inscricaoEstadual;
	}

	public void setInscricaoEstadual(String inscricaoEstadual)
	{
		this.inscricaoEstadual = inscricaoEstadual;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public List<Pessoa> getPessoas()
	{
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas)
	{
		this.pessoas = pessoas;
	}	
}
