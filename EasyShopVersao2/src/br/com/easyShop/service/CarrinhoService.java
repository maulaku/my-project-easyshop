package br.com.easyShop.service;

import br.com.easyShop.model.Carrinho;
import br.com.easyShop.persistencia.DAO.CarrinhoDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class CarrinhoService extends BaseServiceAtta
{
	public void inserir(Carrinho carrinho)
	{
		try 
		{
			new CarrinhoDAO().salvar(carrinho);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
}
