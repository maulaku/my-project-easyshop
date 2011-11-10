package br.com.easyShop.persistencia.DAO;

import java.util.List;

import br.com.easyShop.model.Categoria;
import br.com.easyShop.persistencia.DAO.baseDAO.BaseDAOAtta;
import br.com.easyShop.persistencia.utils.QuerySQL;
import br.com.easyShop.utils.Constantes;

public class CategoriaDAO extends BaseDAOAtta{


	public List<Categoria> getAllCategorias(int profundidade) throws Exception
	{
		QuerySQL query = new QuerySQL();
			
		query.add("SELECT *");
		query.add(" FROM Categoria");
		
		return obtem(Categoria.class, query, profundidade);
	}

	public List<Categoria> getCategoriasSub(Categoria categoria, int profundidade) throws Exception
	{
		QuerySQL query = new QuerySQL();
			
		query.add("SELECT *");
		query.add(" FROM Categoria");
		query.add(" WHERE fkCategoria = ?", categoria.getPkCategoria());
		
		return obtem(Categoria.class, query, profundidade);		
	}

	public List<Categoria> getCategorias(int profundidade) throws Exception
	{
		QuerySQL query = new QuerySQL();

		query.add("SELECT *");
		query.add(" FROM Categoria");
		query.add(" WHERE fkCategoria IS NOT NULL");
		
		return obtem(Categoria.class, query, profundidade);		
	}
	
	public List<Categoria> getTodasCategoriasPai(int profundidade) throws Exception
	{
		QuerySQL query = new QuerySQL();
		
		query.add("SELECT *");
		query.add(" FROM Categoria");
		query.add(" WHERE TIPO = ?", Constantes.CATEGORIA_PAI);

		return obtem(Categoria.class, query, profundidade);
	}
}
