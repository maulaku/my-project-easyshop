package br.com.easyShop.model;

import java.util.Calendar;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import br.com.easyShop.utils.Constantes;

@Entity
@Table(name="pessoafisica", schema="easy")
public class PessoaFisica {
	
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pkPessoaFisica;
	private String nome;
	private String apelido;
	private String rg;
	private String cpf;
	@Temporal(TemporalType.DATE)
	private Calendar dataNascimento;
	private String sexo;
	private int status = Constantes.STATUS_ATIVO;
	@OneToMany
	private List<Pessoa> pessoas;
				
	public PessoaFisica() {}
	
	public List<Pessoa> getPessoas()
	{
		return pessoas;
	}

	public void setPessoas(List<Pessoa> pessoas)
	{
		this.pessoas = pessoas;
	}

	public int getPkPessoaFisica()
	{
		return pkPessoaFisica;
	}

	public void setPkPessoaFisica(int pkPessoaFisica)
	{
		this.pkPessoaFisica = pkPessoaFisica;
	}
	
	public Calendar getDataNascimento()
	{
		return dataNascimento;
	}

	public void setDataNascimento(Calendar dataNascimento)
	{
		this.dataNascimento = dataNascimento;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String getApelido() {
		return apelido;
	}
	
	public void setApelido(String apelido) {
		this.apelido = apelido;
	}
	
	public String getRg() {
		return rg;
	}
	
	public void setRg(String rg) {
		this.rg = rg;
	}
	
	public String getCpf() {
		return cpf;
	}
	
	public void setCpf(String cpf) {
		this.cpf = cpf;
	}
	
	
	public String getSexo() {
		return sexo;
	}
	
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	
	
}
