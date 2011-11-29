package br.com.easyShop.service;

import java.util.Arrays;
import java.util.List;

import br.com.easyShop.comunicacao.ResultJava;
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
			return new UsuarioDAO().getUsuarios(3);
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
			return new UsuarioDAO().getUsuarioNome(nome, 3);
		}
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	public ResultJava getUsuarioId(long id) {
		try
		{
			return new ResultJava(new UsuarioDAO().getUsuarioId(id, 3));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao buscar clientes!\n" + e }));
		}
	}
}
