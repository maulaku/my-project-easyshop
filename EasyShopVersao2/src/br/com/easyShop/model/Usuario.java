package br.com.easyShop.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Column;
import br.com.easyShop.utils.Constantes;
import javax.persistence.ManyToOne;
import javax.persistence.JoinColumn;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.OneToMany;

@Entity
public class Usuario
{
	/*-*-*-* Variaveis e Objetos Privados *-*-*-*/
	@Id @GeneratedValue(strategy=GenerationType.IDENTITY)
	private long pkUsuario;
	@Column(length=500)
	private String login;
	@Column(length=500)
	private String senha;
	private int status = Constantes.STATUS_ATIVO;

	@ManyToOne @JoinColumn(name="fkPessoa")
	private Pessoa pessoa;

	@OneToMany(mappedBy="usuario")
	private List<UsuarioTela> usuarioTelas;


	/*-*-*-* Construtores *-*-*-*/
	public Usuario() { }

	/*-*-*-* Metodos Gets e Sets *-*-*-*/
	public long getPkUsuario() { return pkUsuario; }
	public void setPkUsuario(long pkUsuario) { this.pkUsuario = pkUsuario; }

	public String getLogin() { return login; }
	public void setLogin(String login) { this.login = login; }

	public String getSenha() { return senha; }
	public void setSenha(String senha) { this.senha = senha; }

	public int getStatus() { return status; }
	public void setStatus(int status) { this.status = status; }

	public Pessoa getPessoa() { return pessoa; }
	public void setPessoa(Pessoa pessoa) { this.pessoa = pessoa; }

	public List<UsuarioTela> getUsuarioTelas() { if(usuarioTelas==null) { usuarioTelas = new ArrayList<UsuarioTela>(); } return usuarioTelas; }
	public void setUsuarioTelas(List<UsuarioTela> usuarioTelas) { this.usuarioTelas = usuarioTelas; }

	@Override
	public String toString() {
		return login;
	}
}