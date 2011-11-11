package br.com.easyShop.persistencia.DAO;

import java.util.List;
import br.com.easyShop.model.Estado;
import br.com.easyShop.model.Pais;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class EstadoDAO extends BaseDAOAtta {

	public List<Estado> getEstadosPais(Pais pais, int profundidade) throws Exception {
		QuerySQL query = new QuerySQL();

		query.add("SELECT *");
		query.add(" FROM Estado");
		query.add(" WHERE fkPais = ?", pais.getPkPais());

		return obtem(Estado.class, query, profundidade);
	}
}
