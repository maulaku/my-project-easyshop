package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import org.hibernate.annotations.Type;
import utils.data.Data;
import br.com.easyShop.utils.Constantes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class PessoaFisica
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkPessoaFisica;
	@Column(length=500)
	private String nome;
	@Column(length=500)
	private String apelido;
	@Column(length=500)
	private String cpf;
	@Column(length=500)
	private String rg;
	@Type(type="timestamp")
	private Data dataNascimento;
	@Column(length=500)
	private String sexo;
	private Integer status = Constantes.STATUS_ATIVO;

	@OneToMany(mappedBy="pessoaFisica")
	private List<Pessoa> pessoas;


	/*-*-*-* Construtores *-*-*-*/
	public PessoaFisica() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public Long getPkPessoaFisica() { return pkPessoaFisica; }
	public void setPkPessoaFisica(Long pkPessoaFisica) { this.pkPessoaFisica = pkPessoaFisica; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public String getApelido() { return apelido; }
	public void setApelido(String apelido) { this.apelido = apelido; }

	public String getCpf() { return cpf; }
	public void setCpf(String cpf) { this.cpf = cpf; }

	public String getRg() { return rg; }
	public void setRg(String rg) { this.rg = rg; }

	public Data getDataNascimento() { return dataNascimento; }
	public void setDataNascimento(Data dataNascimento) { this.dataNascimento = dataNascimento; }

	public String getSexo() { return sexo; }
	public void setSexo(String sexo) { this.sexo = sexo; }

	public Integer getStatus() { return status; }
	public void setStatus(Integer status) { this.status = status; }

	public List<Pessoa> getPessoas() { if(pessoas==null) { pessoas = new ArrayList<Pessoa>(); } return pessoas; }
	public void setPessoas(List<Pessoa> pessoas) { this.pessoas = pessoas; }
}