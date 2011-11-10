package br.com.easyShop.service;

import java.util.List;

import br.com.easyShop.model.Contato;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.persistencia.DAO.ContatoDAO;

public class ContatoService {	
	public ContatoService(){ }

	public void salvarContato(Contato contato)
	{
		try 
		{
			new ContatoDAO().salvar(contato);
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}
	
	public List<Contato> getContatos(Pessoa pessoa)
	{
		try
		{
			return new ContatoDAO().getContatosPessoa(pessoa, -1);
		} 
		catch (Exception e)
		{
			e.printStackTrace();
			return null;
		}
	}

}
