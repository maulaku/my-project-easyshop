package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="desejoProduto", schema="easy")
public class DesejoProduto
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkDesejoProduto;
	@ManyToOne @JoinColumn(name="fkDesejo")
	private Desejo desejo;
	@ManyToOne @JoinColumn(name="fkProduto")
	private Produto produto;
	
	public DesejoProduto() {}

	public long getPkDesejoProduto()
	{
		return pkDesejoProduto;
	}

	public void setPkDesejoProduto(long pkDesejoProduto)
	{
		this.pkDesejoProduto = pkDesejoProduto;
	}

	public Desejo getDesejo()
	{
		return desejo;
	}

	public void setDesejo(Desejo desejo)
	{
		this.desejo = desejo;
	}

	public Produto getProduto()
	{
		return produto;
	}

	public void setProduto(Produto produto)
	{
		this.produto = produto;
	}
}
