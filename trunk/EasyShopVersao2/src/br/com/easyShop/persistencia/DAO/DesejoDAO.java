package br.com.easyShop.persistencia.DAO;

import br.com.easyShop.model.Cliente;
import br.com.easyShop.model.Desejo;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class DesejoDAO extends BaseDAOAtta {
	
	public Desejo getDesejo(int profundidade, Cliente cliente) throws Exception {

		QuerySQL query = new QuerySQL();
		query.add("SELECT * ");
		query.add("FROM desejo ");
		query.add("WHERE fkCliente = ?", cliente.getPkCliente());

		return obtemUnico(Desejo.class, query, profundidade);
	}

}
