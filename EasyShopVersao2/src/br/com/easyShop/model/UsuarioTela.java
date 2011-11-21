package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;

@Entity
public class UsuarioTela
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkUsuarioTela;

	@ManyToOne @JoinColumn(name="fkUsuario")
	private Usuario usuario;

	@ManyToOne @JoinColumn(name="fkTela")
	private Tela tela;

	@ManyToOne @JoinColumn(name="fkTipoPermissao")
	private TipoPermissao tipoPermissao;


	/*-*-*-* Construtores *-*-*-*/
	public UsuarioTela() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkUsuarioTela() { return pkUsuarioTela; }
	public void setPkUsuarioTela(long pkUsuarioTela) { this.pkUsuarioTela = pkUsuarioTela; }

	public Usuario getUsuario() { return usuario; }
	public void setUsuario(Usuario usuario) { this.usuario = usuario; }

	public Tela getTela() { return tela; }
	public void setTela(Tela tela) { this.tela = tela; }

	public TipoPermissao getTipoPermissao() { return tipoPermissao; }
	public void setTipoPermissao(TipoPermissao tipoPermissao) { this.tipoPermissao = tipoPermissao; }
	
	public String toString() {
		return usuario.getLogin();
	}
}