package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class Cidade
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkCidade;
	@Column(length=500)
	private String nome;

	@ManyToOne @JoinColumn(name="fkEstado")
	private Estado estado;

	@OneToMany(mappedBy="cidade")
	private List<Endereco> enderecos;


	/*-*-*-* Construtores *-*-*-*/
	public Cidade() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public Long getPkCidade() { return pkCidade; }
	public void setPkCidade(Long pkCidade) { this.pkCidade = pkCidade; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public Estado getEstado() { return estado; }
	public void setEstado(Estado estado) { this.estado = estado; }

	public List<Endereco> getEnderecos() { if(enderecos==null) { enderecos = new ArrayList<Endereco>(); } return enderecos; }
	public void setEnderecos(List<Endereco> enderecos) { this.enderecos = enderecos; }
	
	public String toString() {
		return nome;
	}
}