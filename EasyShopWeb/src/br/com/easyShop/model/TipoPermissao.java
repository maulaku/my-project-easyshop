package br.com.easyShop.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tipopermissao", schema="easy")
public class TipoPermissao
{
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkTipoPermissao;
	private String nome;
	@OneToMany
	private List<UsuarioTela> permissoes;
	
	public TipoPermissao() {}

	public long getPkTipoPermissao()
	{
		return pkTipoPermissao;
	}

	public void setPkTipoPermissao(long pkTipoPermissao)
	{
		this.pkTipoPermissao = pkTipoPermissao;
	}

	public List<UsuarioTela> getPermissoes()
	{
		return permissoes;
	}

	public void setPermissoes(List<UsuarioTela> permissoes)
	{
		this.permissoes = permissoes;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public String toString(){  
        return this.nome;  
	} 
}
