package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Usuario;
import br.com.easyShop.model.UsuarioTela;
import br.com.easyShop.persistencia.DAO.UsuarioTelaDAO;

public class UsuarioTelaService {
	public void inserirUsuarioTela(UsuarioTela usuarioTela){
		try
		{
			UsuarioTelaDAO.inserir(usuarioTela);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<UsuarioTela> getUsuarioTelas(Usuario usuario){
		UsuarioTelaDAO usuarioTelaDAO = new UsuarioTelaDAO();
		return usuarioTelaDAO.getUsuarioTelas(usuario);
	}
	
	public void excluirUsuarioTela(UsuarioTela usuarioTela){
		try
		{
			UsuarioTelaDAO.remover(usuarioTela);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
