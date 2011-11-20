package br.com.easyShop.service;

import java.util.Arrays;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.persistencia.DAO.ClienteDAO;

public class ClienteService {
	
	public ResultJava salvar(Cliente cliente) {
		try
		{
			return new ResultJava(new ClienteDAO().salvar(cliente));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao inserir cliente!\n" + e }));
		}
	}

}
