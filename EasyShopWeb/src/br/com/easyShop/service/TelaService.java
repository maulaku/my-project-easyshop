package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Tela;
import br.com.easyShop.persistencia.DAO.TelaDAO;

public class TelaService {

	public List<Tela> getTelas()
	{
		try
		{
			return new TelaDAO().getTelas(-1);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}
}