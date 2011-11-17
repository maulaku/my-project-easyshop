package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.easyShop.utils.Constantes;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class Marca
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkMarca;
	@Column(length=500)
	private String nome;
	private int status = Constantes.STATUS_ATIVO;

	@OneToMany(mappedBy="marca")
	private List<Produto> produtos;


	/*-*-*-* Construtores *-*-*-*/
	public Marca() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkMarca() { return pkMarca; }
	public void setPkMarca(long pkMarca) { this.pkMarca = pkMarca; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }

	public List<Produto> getProdutos() { if(produtos==null) { produtos = new ArrayList<Produto>(); } return produtos; }
	public void setProdutos(List<Produto> produtos) { this.produtos = produtos; }
}