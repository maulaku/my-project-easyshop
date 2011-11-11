package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Usuario;
import br.com.easyShop.model.UsuarioTela;
import br.com.easyShop.persistencia.DAO.UsuarioTelaDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class UsuarioTelaService extends BaseServiceAtta 
{
	public void inserirUsuarioTela(UsuarioTela usuarioTela)
	{
		try
		{
			new UsuarioTelaDAO().salvar(usuarioTela);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public List<UsuarioTela> getUsuarioTelas(Usuario usuario)
	{
		try
		{
			return new UsuarioTelaDAO().getUsuarioTelas(usuario, -1);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public void excluirUsuarioTela(UsuarioTela usuarioTela)
	{
		try
		{
			new UsuarioTelaDAO().remover(usuarioTela);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
		}
	}
}
