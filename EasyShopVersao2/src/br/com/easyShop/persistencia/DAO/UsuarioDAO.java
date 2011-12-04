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
		 query.add(" FROM usuario,cliente,pessoa");
		 query.add(" WHERE usuario.fkpessoa = pessoa.pkpessoa");
		 query.add(" AND pessoa.pkpessoa != cliente.fkpessoa");
		
		 return obtem(Usuario.class, query, profundidade);
    }
    
    public Usuario getUsuarioNome(String nome, String senha, int profundidade) throws Exception
    {
    	QuerySQL query = new QuerySQL();
		
    	 query.add("SELECT *");
		 query.add(" FROM usuario");
		 query.add(" WHERE login = ?", nome);
		 query.add(" AND senha = ?", senha);
		 
		 return obtemUnico(Usuario.class, query, profundidade);
    }
    
    public Usuario getUsuarioId(long id, int profundidade) throws Exception
    {
    	QuerySQL query = new QuerySQL();
		
		 query.add("SELECT *");
		 query.add(" FROM Usuario");
		 query.add(" WHERE fkpessoa = ?", id);
		 
		 return obtemUnico(Usuario.class, query, profundidade);
    }
}
