package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Usuario;
import br.com.easyShop.persistencia.DAO.UsuarioDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class UsuarioService extends BaseServiceAtta
{

	public void salvar(Usuario usuario) 
	{
		try 
		{
			new UsuarioDAO().salvar(usuario);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	public List<Usuario> getUsuarios()
	{
		try
		{
			return new UsuarioDAO().getUsuarios(-1);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public Usuario getUsuario(String nome) 
	{
		try
		{
			return new UsuarioDAO().getUsuarioNome(nome, -1);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	
}
