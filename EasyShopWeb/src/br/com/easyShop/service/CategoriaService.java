package br.com.easyShop.service;

import java.util.Arrays;
import java.util.List;

import br.com.easyShop.comunicacao.ResultJava;
import br.com.easyShop.model.Categoria;
import br.com.easyShop.persistencia.DAO.CategoriaDAO;

public class CategoriaService {

	public List<Categoria> getAllCategorias(){
		CategoriaDAO categoriaDao = new CategoriaDAO();
		return categoriaDao.getAllCategorias();
	}

	public void inserirCategoria(Categoria categoria){
		try {
			CategoriaDAO.inserir(categoria);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public List<Categoria> getSubCategorias(Categoria categoria){
		CategoriaDAO categoriaDao = new CategoriaDAO();
		return categoriaDao.getCategoriasSub(categoria);
	}

	public List<Categoria> getCategorias(){
		CategoriaDAO categoriaDao = new CategoriaDAO();
		return categoriaDao.getCategorias();
	}
	
	/**
	 * Busca todas categorias 
	 * @author Jean
	 * @Aba MainEasyShop
	 * @return lista de categorias
	 */
	public ResultJava getTodasCategoriasPai()
	{
		try
		{
			return new ResultJava(true, new CategoriaDAO().getTodasCategoriasPai());
		} 
		catch (Exception e)
		{
			return new ResultJava(false, Arrays.asList(new String[] {"Erro ao buscar categorias"}));
		}
	}
}
