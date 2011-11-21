package br.com.easyShop.persistencia.DAO;

import br.com.easyShop.model.Cliente;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class ClienteDAO extends BaseDAOAtta{

	 public Cliente getClienteNome(String nome, int profundidade) throws Exception
	    {
	    	QuerySQL query = new QuerySQL();
			
			 query.add("SELECT *");
			 query.add(" FROM Cliente");
			 query.add(" WHERE login = ?", nome);
			 
			 return obtemUnico(Cliente.class, query, profundidade);
	    }
}
