package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import br.com.easyShop.utils.Constantes;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class Desejo
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkDesejo;
	private int status = Constantes.STATUS_ATIVO;

	@ManyToOne @JoinColumn(name="fkCliente")
	private Cliente cliente;

	@OneToMany(mappedBy="desejo")
	private List<DesejoProduto> desejoProdutos;


	/*-*-*-* Construtores *-*-*-*/
	public Desejo() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkDesejo() { return pkDesejo; }
	public void setPkDesejo(long pkDesejo) { this.pkDesejo = pkDesejo; }

	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }

	public Cliente getCliente() { return cliente; }
	public void setCliente(Cliente cliente) { this.cliente = cliente; }

	public List<DesejoProduto> getDesejoProdutos() { if(desejoProdutos==null) { desejoProdutos = new ArrayList<DesejoProduto>(); } return desejoProdutos; }
	public void setDesejoProdutos(List<DesejoProduto> desejoProdutos) { this.desejoProdutos = desejoProdutos; }
}