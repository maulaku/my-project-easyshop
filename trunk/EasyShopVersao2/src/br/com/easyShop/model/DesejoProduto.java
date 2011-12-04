package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.easyShop.utils.Constantes;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class DesejoProduto
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkDesejoProduto;
	private Integer status = Constantes.STATUS_ATIVO;

	@ManyToOne @JoinColumn(name="fkDesejo")
	private Desejo desejo;

	@ManyToOne @JoinColumn(name="fkProduto")
	private Produto produto;


	/*-*-*-* Construtores *-*-*-*/
	public DesejoProduto() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkDesejoProduto() { return pkDesejoProduto; }
	public void setPkDesejoProduto(long pkDesejoProduto) { this.pkDesejoProduto = pkDesejoProduto; }

	public Integer getStatus() { return status; }
	public void setStatus(Integer status) { this.status = status; }

	public Desejo getDesejo() { return desejo; }
	public void setDesejo(Desejo desejo) { this.desejo = desejo; }

	public Produto getProduto() { return produto; }
	public void setProduto(Produto produto) { this.produto = produto; }
}