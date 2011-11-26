package br.com.easyShop.service;

import java.util.Arrays;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Pedido;
import br.com.easyShop.persistencia.DAO.PedidoProdutoDAO;
import br.com.easyShop.service.base.BaseServiceAtta;

public class PedidoProdutoService extends BaseServiceAtta {
	
	public ResultJava getPedidosProdutoCliente(Pedido pedido) {
		try
		{
			return new ResultJava( new PedidoProdutoDAO().getPedidosProdutosCliente(pedido, -1));
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao buscar pedidoProduto\n" + e }));
		}
	}
}
