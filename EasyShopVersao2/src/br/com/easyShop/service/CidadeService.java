package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Cidade;
import br.com.easyShop.model.Estado;
import br.com.easyShop.persistencia.DAO.CidadeDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class CidadeService extends BaseServiceAtta
{
	
	public List<Cidade> getCidades(Estado estado)
	{
		try
		{
			return new CidadeDAO().getCidades(estado, -1);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
