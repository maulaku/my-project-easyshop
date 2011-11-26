package br.com.easyShop.service;

import java.util.Arrays;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Pedido;
import br.com.easyShop.persistencia.DAO.PedidoDAO;
import br.com.easyShop.persistencia.DAO.PedidoProdutoDAO;
import br.com.easyShop.persistencia.DAO.PerfilPagamentoDAO;

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
	
	public ResultJava salvarPedido(Pedido pedido) {
		try
		{
			int i;
			
			PerfilPagamentoDAO perfilPagamento = new PerfilPagamentoDAO();
			perfilPagamento.salvar(pedido.getPerfilPagamento());
			
			PedidoDAO pedidoDAO = new PedidoDAO();
			pedidoDAO.salvar(pedido);
			
			for(i=0;i<pedido.getPedidoProdutos().size();i++){
				new PedidoProdutoDAO().salvar(pedido.getPedidoProdutos().get(i));
			}
			
			return null;
		} 
		catch (Exception e) 
		{
			return new ResultJava(false, Arrays.asList(new String[] { "Erro ao salvar pedidos!\n" + e }));
		}
	}
}
