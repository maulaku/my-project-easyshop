package br.com.easyShop.service;

import br.com.easyShop.model.CarrinhoProduto;
import br.com.easyShop.persistencia.DAO.CarrinhoProdutoDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class CarrinhoProdutoService extends BaseServiceAtta
{
	public void inserir(CarrinhoProduto carrinhoProduto)
	{
		try 
		{
			new CarrinhoProdutoDAO().salvar(carrinhoProduto);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		
	}
}
