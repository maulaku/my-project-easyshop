package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Pais;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class PaisDAO extends BaseDAOAtta {

	public List<Pais> getPais(int profundidade) throws Exception {

		QuerySQL query = new QuerySQL();

		query.add("SELECT *");
		query.add(" FROM PAIS");

		return obtem(Pais.class, query, profundidade);
	}

}
