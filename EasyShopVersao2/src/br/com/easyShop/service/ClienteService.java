package br.com.easyShop.service;

import java.util.Arrays;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Pessoa;
import br.com.easyShop.persistencia.DAO.ClienteDAO;
import br.com.easyShop.persistencia.DAO.PessoaDAO;

public class ClienteService {
	
	public void inserirCliente(Cliente cliente) {
		try {
			new ClienteDAO().inserir(cliente);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
//	public ResultJava salvar(Cliente cliente) {
//		try
//		{
//			return new ResultJava(new ClienteDAO().inserir(cliente));
//		} 
//		catch (Exception e) 
//		{
//			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao inserir cliente!\n" + e }));
//		}
//	}

}
