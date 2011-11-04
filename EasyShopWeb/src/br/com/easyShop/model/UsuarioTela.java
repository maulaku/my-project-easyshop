package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="usuariotela", schema="easy")
public class UsuarioTela
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkUsuarioTela;
	@ManyToOne @JoinColumn(name="fkTipoPermissao")
	private TipoPermissao tipoPermissao; 
	@ManyToOne @JoinColumn(name="fkUsuario")
	private Usuario usuario;
	@ManyToOne @JoinColumn(name="fkTela")
	private Tela tela;
	
	public UsuarioTela() {}

	public long getPkUsuarioTela()
	{
		return pkUsuarioTela;
	}


	public void setPkUsuarioTela(long pkUsuarioTela)
	{
		this.pkUsuarioTela = pkUsuarioTela;
	}


	public TipoPermissao getTipoPermissao()
	{
		return tipoPermissao;
	}


	public void setTipoPermissao(TipoPermissao tipoPermissao)
	{
		this.tipoPermissao = tipoPermissao;
	}


	public Usuario getUsuario()
	{
		return usuario;
	}

	public void setUsuario(Usuario usuario)
	{
		this.usuario = usuario;
	}

	public Tela getTela() {
		return tela;
	}

	public void setTela(Tela tela) {
		this.tela = tela;
	}
	
	
	public String toString(){  
        return this.tela.getNome();  
	}

}
