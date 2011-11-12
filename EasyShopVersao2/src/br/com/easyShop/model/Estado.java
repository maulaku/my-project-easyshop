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
public class Estado
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkEstado;
	@Column(length=500)
	private String sigla;
	@Column(length=500)
	private String nome;

	@ManyToOne @JoinColumn(name="fkPais")
	private Pais pais;

	@OneToMany(mappedBy="estado")
	private List<Cidade> cidades;


	/*-*-*-* Construtores *-*-*-*/
	public Estado() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public Long getPkEstado() { return pkEstado; }
	public void setPkEstado(Long pkEstado) { this.pkEstado = pkEstado; }

	public String getSigla() { return sigla; }
	public void setSigla(String sigla) { this.sigla = sigla; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public Pais getPais() { return pais; }
	public void setPais(Pais pais) { this.pais = pais; }

	public List<Cidade> getCidades() { if(cidades==null) { cidades = new ArrayList<Cidade>(); } return cidades; }
	public void setCidades(List<Cidade> cidades) { this.cidades = cidades; }
	
	public String toString() {
		return nome;
	}
}