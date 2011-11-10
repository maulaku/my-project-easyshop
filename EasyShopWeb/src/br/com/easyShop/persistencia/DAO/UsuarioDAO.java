package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Usuario;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;


public class UsuarioDAO extends BaseDAOAtta
{
    public List<Usuario> getUsuarios(int profundidade) throws Exception  
	 {
		 QuerySQL query = new QuerySQL();
			
		 query.add("SELECT *");
		 query.add(" FROM Usuario");
		
		 return obtem(Usuario.class, query, profundidade);
    }
    
    public Usuario getUsuarioNome(String nome, int profundidade) throws Exception
    {
    	QuerySQL query = new QuerySQL();
		
		 query.add("SELECT *");
		 query.add("FROM Usuario");
		 query.add("WHERE login = ?", nome);
		 
		 return obtemUnico(Usuario.class, query, profundidade);
    }
}
