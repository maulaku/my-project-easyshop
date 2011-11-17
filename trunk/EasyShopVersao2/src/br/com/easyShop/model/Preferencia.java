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
public class Preferencia
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkPreferencia;
	@Column(length=500)
	private String nome;
	private int status = Constantes.STATUS_ATIVO;

	@ManyToOne @JoinColumn(name="fkCliente")
	private Cliente cliente;

	@ManyToOne @JoinColumn(name="fkCategoria")
	private Categoria categoria;


	/*-*-*-* Construtores *-*-*-*/
	public Preferencia() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkPreferencia() { return pkPreferencia; }
	public void setPkPreferencia(long pkPreferencia) { this.pkPreferencia = pkPreferencia; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }

	public Cliente getCliente() { return cliente; }
	public void setCliente(Cliente cliente) { this.cliente = cliente; }

	public Categoria getCategoria() { return categoria; }
	public void setCategoria(Categoria categoria) { this.categoria = categoria; }
}