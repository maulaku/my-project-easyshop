package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Tela;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class TelaDAO extends BaseDAOAtta
{
	 public List<Tela> getTelas(int profundidade) throws Exception  
	 {		 
		 QuerySQL query = new QuerySQL();
			
		 query.add("SELECT *");
		 query.add(" FROM Tela");
		
		 return obtem(Tela.class, query, profundidade);
	 }

}
