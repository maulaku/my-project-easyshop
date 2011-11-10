package br.com.easyShop.service;

import br.com.easyShop.model.Endereco;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.persistencia.DAO.EnderecoDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class EnderecoService extends BaseServiceAtta
{
	public void salvar(Endereco endereco)
	{
		try 
		{
			new EnderecoDAO().salvar(endereco);
		}
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public Endereco getEnderecoPessoa(Pessoa pessoa)
	{
		try
		{
			return new EnderecoDAO().getEnderecoPessoa(pessoa, -1);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

	public void atualizar(Endereco endereco)
	{
		salvar(endereco);
	}
	
}
