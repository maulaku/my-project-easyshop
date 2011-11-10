package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Marca;
import br.com.easyShop.persistencia.DAO.MarcaDAO;

public class MarcaService {

	public void inserir(Marca marca) 
	{
		try 
		{
			new MarcaDAO().salvar(marca);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public List<Marca> getMarcas()
	{
		try
		{
			return new MarcaDAO().getMarcas(-1);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
