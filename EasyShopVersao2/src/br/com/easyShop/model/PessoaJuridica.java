package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.easyShop.utils.Constantes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class PessoaJuridica
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkPessoaJuridica;
	@Column(length=500)
	private String razaoSocial;
	@Column(length=500)
	private String nomeFantasia;
	@Column(length=500)
	private String cnpj;
	@Column(length=500)
	private String inscricaoEstadual;
	private int status = Constantes.STATUS_ATIVO;

	@OneToMany(mappedBy="pessoaJuridica")
	private List<Pessoa> pessoas;


	/*-*-*-* Construtores *-*-*-*/
	public PessoaJuridica() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkPessoaJuridica() { return pkPessoaJuridica; }
	public void setPkPessoaJuridica(long pkPessoaJuridica) { this.pkPessoaJuridica = pkPessoaJuridica; }

	public String getRazaoSocial() { return razaoSocial; }
	public void setRazaoSocial(String razaoSocial) { this.razaoSocial = razaoSocial; }

	public String getNomeFantasia() { return nomeFantasia; }
	public void setNomeFantasia(String nomeFantasia) { this.nomeFantasia = nomeFantasia; }

	public String getCnpj() { return cnpj; }
	public void setCnpj(String cnpj) { this.cnpj = cnpj; }

	public String getInscricaoEstadual() { return inscricaoEstadual; }
	public void setInscricaoEstadual(String inscricaoEstadual) { this.inscricaoEstadual = inscricaoEstadual; }

	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }

	public List<Pessoa> getPessoas() { if(pessoas==null) { pessoas = new ArrayList<Pessoa>(); } return pessoas; }
	public void setPessoas(List<Pessoa> pessoas) { this.pessoas = pessoas; }
	
	public String toString() {
		return cnpj;
	}
}