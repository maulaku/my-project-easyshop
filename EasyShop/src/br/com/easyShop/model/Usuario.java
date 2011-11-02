package br.com.easyShop.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import br.com.easyShop.utils.Constantes;


@Entity
@Table(name="usuario", schema="easy")
public class Usuario {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int pkUsuario;
	private String login;
	private String senha;
	private int status = Constantes.STATUS_ATIVO;
    @ManyToOne @JoinColumn(name="fkPessoa")    
	private Pessoa pessoa;
    @OneToMany
    private List<UsuarioTela> permissoes;
	
    public Usuario () {}    
    
	public int getPkUsuario()
	{
		return pkUsuario;
	}


	public void setPkUsuario(int pkUsuario)
	{
		this.pkUsuario = pkUsuario;
	}


	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public int getStatus()
	{
		return status;
	}

	public void setStatus(int status)
	{
		this.status = status;
	}

	public String getSenha()
	{
		return senha;
	}

	public void setSenha(String senha)
	{
		this.senha = senha;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}
	
	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}
	
	public String toString(){  
        return this.login;  
	}

	public List<UsuarioTela> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<UsuarioTela> permissoes) {
		this.permissoes = permissoes;
	} 
	
}
