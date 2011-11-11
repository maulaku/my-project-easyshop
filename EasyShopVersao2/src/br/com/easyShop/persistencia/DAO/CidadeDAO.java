package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Cidade;
import br.com.easyShop.model.Estado;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class CidadeDAO extends BaseDAOAtta {
	
	public List<Cidade> getCidades(Estado estado, int profundidade) throws Exception
	{	
		QuerySQL query = new QuerySQL();
		
		query.add("SELECT *");
		query.add(" FROM Cidade");
		query.add(" WHERE fkEstado = ?", estado.getPkEstado());

		return obtem(Cidade.class, query, profundidade);
	}

}
