package br.com.easyShop.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import utils.data.Data;
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
	@Type(type="timestamp")
	private Data dataNascimento;
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
	
	public Date getDataNascimento()
	{
		return dataNascimento.getTime();
	}

	public void setDataNascimento(Date dataNascimento)
	{
		Data data = new Data(dataNascimento);
		this.dataNascimento = data;
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
