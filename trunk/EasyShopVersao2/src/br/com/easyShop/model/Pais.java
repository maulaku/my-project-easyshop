package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class Pais
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkPais;
	@Column(length=500)
	private String nome;

	@OneToMany(mappedBy="pais")
	private List<Estado> estados;


	/*-*-*-* Construtores *-*-*-*/
	public Pais() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkPais() { return pkPais; }
	public void setPkPais(long pkPais) { this.pkPais = pkPais; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public List<Estado> getEstados() { if(estados==null) { estados = new ArrayList<Estado>(); } return estados; }
	public void setEstados(List<Estado> estados) { this.estados = estados; }
	
	public String toString() {
		return nome;
	}
}