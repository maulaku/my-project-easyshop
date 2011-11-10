package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.TipoPermissao;
import br.com.easyShop.persistencia.DAO.TipoPermissaoDAO;

public class TipoPermissaoService 
{
	public List<TipoPermissao> getTipoPermissao()
	{
		try
		{
			return new TipoPermissaoDAO().geTipoPermissao(-1);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}
