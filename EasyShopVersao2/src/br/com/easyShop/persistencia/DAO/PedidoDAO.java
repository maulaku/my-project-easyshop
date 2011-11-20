package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Pedido;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class PedidoDAO extends BaseDAOAtta{
	
	public List<Pedido> getPedidosCliente(Cliente cliente, int profundidade) throws Exception {
		QuerySQL query = new QuerySQL();

		query.add("SELECT *");
		query.add(" FROM Pedido");
		query.add(" WHERE fkCliente = ?", cliente.getPkCliente());

		return obtem(Pedido.class, query, profundidade);
	}
	
}
