package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Produto;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;

public class ProdutoDAO extends BaseDAOAtta {
	
	public List<Produto> getProdutos(int profundidade) throws Exception  
	{
		QuerySQL query = new QuerySQL();
		
		query.add("SELECT *");
		query.add(" FROM Produto");
		
		return obtem(Produto.class, query, profundidade);
	}
	
	/**
	 * Buscas os produtos atraés do nome
	 * @author Jean
	 * @param nome
	 * @param profundidade
	 * @return lista de produtos
	 * @throws Exception
	 */
	public List<Produto> getProdutosNome(String nome, int profundidade) throws Exception 
	{
		QuerySQL query = new QuerySQL();
		
		query.add("SELECT *");
		query.add(" FROM Produto");
		query.add(" WHERE nome like '%?%'", nome);
		
		return obtem(Produto.class, query, profundidade);
	}

}
