package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import br.com.easyShop.utils.Constantes;

@Entity
@Table(name="contato", schema="easy")
public class Contato
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkContato;
	private int  tipo; 
	private String contato;
	private int status = Constantes.STATUS_ATIVO;
	@ManyToOne @JoinColumn(name="fkPessoa")
	private Pessoa pessoa;

	public Contato() {}

	public long getPkContato()
	{
		return pkContato;
	}

	public void setPkContato(long pkContato)
	{
		this.pkContato = pkContato;
	}

	public String getContato()
	{
		return contato;
	}

	public void setContato(String contato)
	{
		this.contato = contato;
	}

	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public Pessoa getPessoa()
	{
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa)
	{
		this.pessoa = pessoa;
	}

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}
	
}
