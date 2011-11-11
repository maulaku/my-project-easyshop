package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.easyShop.utils.Constantes;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class Contato
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkContato;
	@Column(length=500)
	private String contato;
	private int tipo;
	private int status = Constantes.STATUS_ATIVO;

	@ManyToOne @JoinColumn(name="fkPessoa")
	private Pessoa pessoa;


	/*-*-*-* Construtores *-*-*-*/
	public Contato() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkContato() { return pkContato; }
	public void setPkContato(long pkContato) { this.pkContato = pkContato; }

	public String getContato() { return contato; }
	public void setContato(String contato) { this.contato = contato; }

	public int getTipo() { return tipo; }
	public void setTipo(int tipo) { this.tipo = tipo; }

	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }

	public Pessoa getPessoa() { return pessoa; }
	public void setPessoa(Pessoa pessoa) { this.pessoa = pessoa; }
}