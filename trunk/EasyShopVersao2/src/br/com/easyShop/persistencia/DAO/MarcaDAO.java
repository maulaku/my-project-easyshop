package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Marca;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class MarcaDAO extends BaseDAOAtta 
{
	public List<Marca> getMarcas(int profundidade) throws Exception
	{
		QuerySQL query = new QuerySQL();
		
		query.add("SELECT *");
		query.add(" FROM Marca");
		
		return obtem(Marca.class, query, profundidade);
	}

}
