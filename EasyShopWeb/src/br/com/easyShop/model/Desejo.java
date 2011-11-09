package br.com.easyShop.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.easyShop.utils.Constantes;

@Entity
@Table(name="desejo", schema="easy")
public class Desejo
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkDesejo;
	private String nome;
	private Integer status = Constantes.STATUS_ATIVO;
	@ManyToOne @JoinColumn(name="fkCliente")
	private Cliente cliente;
	@OneToMany (mappedBy="desejo")
	private List<DesejoProduto> desejoProdutos;
	
	public Desejo() {}

	public Long getPkDesejo()
	{
		return pkDesejo;
	}

	public void setPkDesejo(Long pkDesejo)
	{
		this.pkDesejo = pkDesejo;
	}

	public String getNome()
	{
		return nome;
	}

	public void setNome(String nome)
	{
		this.nome = nome;
	}

	public Integer getStatus()
	{
		return status;
	}

	public void setStatus(Integer status)
	{
		this.status = status;
	}

	public Cliente getCliente()
	{
		return cliente;
	}

	public void setCliente(Cliente cliente)
	{
		this.cliente = cliente;
	}

	public List<DesejoProduto> getDesejoProdutos()
	{
		return desejoProdutos;
	}

	public void setDesejoProdutos(List<DesejoProduto> desejoProdutos)
	{
		this.desejoProdutos = desejoProdutos;
	}
}
