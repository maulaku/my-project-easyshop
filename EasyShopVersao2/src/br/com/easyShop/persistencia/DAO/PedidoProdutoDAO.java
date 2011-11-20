package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Pedido;
import br.com.easyShop.model.PedidoProduto;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class PedidoProdutoDAO extends BaseDAOAtta{
	
	public PedidoProduto getPedidosProdutosCliente(Pedido pedido, int profundidade) throws Exception {
		QuerySQL query = new QuerySQL();

		query.add("SELECT *");
		query.add(" FROM PedidoProduto");
		query.add(" WHERE fkPedido = ?", pedido.getPkPedido());

		return obtemUnico(PedidoProduto.class, query, profundidade);
	}

}
