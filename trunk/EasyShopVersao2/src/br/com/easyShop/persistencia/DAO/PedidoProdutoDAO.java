package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Pedido;
import br.com.easyShop.model.PedidoProduto;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

import br.com.easyShop.model.Cliente;


public class PedidoProdutoDAO extends BaseDAOAtta{
	
	public PedidoProduto getPedidosProdutosCliente(Pedido pedido, int profundidade) throws Exception {
		QuerySQL query = new QuerySQL();

		query.add("SELECT *");
		query.add(" FROM PedidoProduto");
		query.add(" WHERE fkPedido = ?", pedido.getPkPedido());

		return obtemUnico(PedidoProduto.class, query, profundidade);
	}
	
	public List<PedidoProduto> getPedidosProdutos(Cliente cliente, int profundidade) throws Exception {
		QuerySQL query = new QuerySQL();

		 query.add("SELECT *");
		 query.add(" FROM pedidoproduto,pedido");
		 query.add(" WHERE pedido.fkcliente = ?", cliente.getPkCliente());
		 query.add(" AND pedidoproduto.fkpedido = pedido.pkpedido");

		 return obtem(PedidoProduto.class, query, profundidade);
	}

}
