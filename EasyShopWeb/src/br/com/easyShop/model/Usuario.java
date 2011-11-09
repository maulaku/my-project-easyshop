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
	private Integer pkUsuario;
	private String login;
	private String senha;
	private Integer status = Constantes.STATUS_ATIVO;
    @ManyToOne @JoinColumn(name="fkPessoa")    
	private Pessoa pessoa;
    @OneToMany (mappedBy="usuario")
    private List<UsuarioTela> permissoes;
	
    public Usuario () {}    
    
	public Integer getPkUsuario() {
		return pkUsuario;
	}

	public void setPkUsuario(Integer pkUsuario) {
		this.pkUsuario = pkUsuario;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public List<UsuarioTela> getPermissoes() {
		return permissoes;
	}

	public void setPermissoes(List<UsuarioTela> permissoes) {
		this.permissoes = permissoes;
	}

	public String toString(){  
        return this.login;  
	}

}
