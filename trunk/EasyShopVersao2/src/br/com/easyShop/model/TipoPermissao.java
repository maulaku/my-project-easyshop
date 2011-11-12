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
public class TipoPermissao
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkTipoPermissao;
	@Column(length=500)
	private String nome;

	@OneToMany(mappedBy="tipoPermissao")
	private List<UsuarioTela> usuarioTelas;


	/*-*-*-* Construtores *-*-*-*/
	public TipoPermissao() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public Long getPkTipoPermissao() { return pkTipoPermissao; }
	public void setPkTipoPermissao(Long pkTipoPermissao) { this.pkTipoPermissao = pkTipoPermissao; }

	public String getNome() { return nome; }
	public void setNome(String nome) { this.nome = nome; }

	public List<UsuarioTela> getUsuarioTelas() { if(usuarioTelas==null) { usuarioTelas = new ArrayList<UsuarioTela>(); } return usuarioTelas; }
	public void setUsuarioTelas(List<UsuarioTela> usuarioTelas) { this.usuarioTelas = usuarioTelas; }
}