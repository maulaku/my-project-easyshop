package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class DesejoProduto
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkDesejoProduto;

	@ManyToOne @JoinColumn(name="fkDesejo")
	private Desejo desejo;

	@ManyToOne @JoinColumn(name="fkProduto")
	private Produto produto;


	/*-*-*-* Construtores *-*-*-*/
	public DesejoProduto() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public Long getPkDesejoProduto() { return pkDesejoProduto; }
	public void setPkDesejoProduto(Long pkDesejoProduto) { this.pkDesejoProduto = pkDesejoProduto; }

	public Desejo getDesejo() { return desejo; }
	public void setDesejo(Desejo desejo) { this.desejo = desejo; }

	public Produto getProduto() { return produto; }
	public void setProduto(Produto produto) { this.produto = produto; }
}