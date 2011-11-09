package br.com.easyShop.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tela", schema="easy")
public class Tela
{
	@Id 
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long pkTela;
	private String nome;
	@OneToMany (mappedBy="tela")
	private List<UsuarioTela> permissoes;
	
	public Tela() {}

	
	public Long getPkTela() {
		return pkTela;
	}


	public void setPkTela(Long pkTela) {
		this.pkTela = pkTela;
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
