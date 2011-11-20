package br.com.easyShop.service;

import java.util.Arrays;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.persistencia.DAO.PedidoDAO;

public class PedidoService {
	
	public ResultJava getPedidosCliente(Cliente cliente) {
		try
		{
			return new ResultJava(true, new PedidoDAO().getPedidosCliente(cliente, -1));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao buscar pedido\n" + e }));
		}
	}
}
