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
	private Long pkTipoPermissao;
	private String nome;
	@OneToMany (mappedBy="tipopermissao")
	private List<UsuarioTela> permissoes;
	
	public TipoPermissao() {}

	public Long getPkTipoPermissao() {
		return pkTipoPermissao;
	}

	public void setPkTipoPermissao(Long pkTipoPermissao) {
		this.pkTipoPermissao = pkTipoPermissao;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public List<UsuarioTela> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<UsuarioTela> permissoes) {
		this.permissoes = permissoes;
	}

	public String toString(){  
        return this.nome;  
	} 
}
