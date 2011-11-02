package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Usuario;
import br.com.easyShop.persistencia.DAO.UsuarioDAO;

public class UsuarioService {

	public void inserirUsuario(Usuario usuario) {
		try {
			UsuarioDAO.inserir(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Usuario> getUsuarios() {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.getUsuarios();
	}

	public Usuario getUsuario(String nome) {
		UsuarioDAO usuarioDAO = new UsuarioDAO();
		return usuarioDAO.getUsuario(nome);
	}

	public void atualizar(Usuario usuario) {
		try {
			UsuarioDAO.atualizar(usuario);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
